package com.utstar.c1handler.task.sop;

import com.alibaba.druid.util.StringUtils;
import com.utstar.c1handler.config.ConfigReader;
import com.utstar.c1handler.entities.*;
import com.utstar.c1handler.enums.ReceiveEventStatus;
import com.utstar.c1handler.repositories.EpgpubNodeDao;
import com.utstar.c1handler.repositories.JdbcDao;
import com.utstar.c1handler.service.*;
import com.utstar.c1handler.util.DownloadUtil;
import com.utstar.c1handler.util.MD5Util;
import com.utstar.c1handler.util.TarUtil;
import com.utstar.c1handler.webservice.sop.CSPRequestProxy;
import com.utstar.c1handler.webservice.sop.CSPResult;
import com.utstar.c1handler.webservice.sop.CtmsSoapBindingStub;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DispatcherTask {
    private static final Logger log = LoggerFactory.getLogger(DispatcherTask.class);
    @Resource
    private ReceiveEventService receiveEventService;

    @Resource
    private ConfigReader configReader;

    @Resource
    private ReceiveNodeService receiveNodeService;

    @Resource
    private EpgpubNodeService epgpubNodeService;

    @Resource
    private EpgpubFileService epgpubFileService;

    @Resource
    private EpgpublogService epgpublogService;

    //上次执行完下次间隔5秒才开始
    @Scheduled(initialDelay =  1000 * 10,fixedDelay = 1000 * 5)
    public void downloadTar(){

        //异步下载接收日志表里面的tar包并存放到指定目录,并生成C1转发日志
        List<ReceiveEvent> events = receiveEventService.findByStatus(ReceiveEventStatus.SUCCESSFULLY_RECEIVE);
        events.forEach(receiveEvent -> {
            {
                try {
                    String localepgfile = configReader.getLocalFilePath() + File.separator + "epg_template_path"
                            + File.separator + receiveEvent.getFileName();
                    if (DownloadUtil.downloadCommandReqFile(receiveEvent.getCmdfileurl(), localepgfile) != 0) {
                        log.error("download epg file fail!"+localepgfile);
                        receiveEvent.setStatus(ReceiveEventStatus.DOWNLOAD_FAIL);
                    }

                    String folder = configReader.getEpgFileUnzipPath();
                    if (configReader.isUncompressEpggroupCodeFolderAble()) {
                        folder = folder + File.separator + receiveEvent.getEpgGroup();
                    }
                    TarUtil.doDecompress(new File(localepgfile), folder);
                    receiveEvent.setStatus(ReceiveEventStatus.DOWNLOAD_SUCESS);
                    saveEpgpubEventlog(receiveEvent, localepgfile);
                } catch (IOException e) {
                    log.error("decompression fail"+e.getStackTrace());
                    receiveEvent.setStatus(ReceiveEventStatus.DOWNLOAD_FAIL);
                }
                //jpa里save就是更新
                receiveEventService.save(receiveEvent);

            }
        });
    }

    //同步下游的结果给第三方（下游返回的结果同步回到上游）
    @Scheduled(initialDelay =  1000 * 10,fixedDelay = 1000 * 20)
    public void syscDatatoThrid(){
        List<ReceiveEvent> receiveEvents = receiveEventService.findByStatus(ReceiveEventStatus.DOWNLOAD_SUCESS);
        if(!CollectionUtils.isEmpty(receiveEvents)) {
            receiveEvents.forEach(receiveEvent -> {
                List<EpgpubEventlog> eventlogs = epgpublogService.findEpgpubEventlogByStatusInAndReceiveeventid(Arrays.asList("0", "1", "2", "3", "4"), receiveEvent.getReceiveEventId());

                List<String> statusList = new ArrayList<>();
                //只要有一条发布日志是发布失败，则返回给上游发布失败
                if (!CollectionUtils.isEmpty(eventlogs)) {
                    /**0、未下发 1、下发成功 2、下发失败 3、处理成功 4、处理失败 */
                    eventlogs.forEach(epgpubEventlog -> {
                        statusList.add(epgpubEventlog.getStatus());
                    });
                }
                //若存在中间状态则停止循环
                if (CollectionUtils.isEmpty(statusList) || statusList.contains("0") || statusList.contains("1")) return;

                try {
                    ReceiveNode receiveNode = receiveNodeService.findReceiveNodeByReceiveNodeId(receiveEvent.getReceiveNodeId());
                    if (receiveNode == null) return;
                    String srccspid = receiveNode.getSrccsp();
                    String srclspid = receiveNode.getSrclsp();
                    String correlateid = receiveEvent.getCorrelateId();
                    String srcurl = receiveNode.getSrcurl();
                    log.info("syscDataToThrid begin:"+srcurl);
                    CSPRequestProxy proxy = new CSPRequestProxy(srcurl);
                    log.info("syscDataToThrid sucess"+srcurl);
                    if(StringUtils.isEmpty(eventlogs.get(0).getLocalresultfileurl()))
                    {
                        log.error("下游还未反馈或反馈失败"+eventlogs.get(0).getEpgpubeventlogid());
                        receiveEvent.setStatus(ReceiveEventStatus.FAILED_HANDLE);
                    } else {
                    	URL url = new URL(srcurl);
                        CtmsSoapBindingStub binding = (CtmsSoapBindingStub) new com.utstar.c1handler.webservice.sop.CSPRequestServiceLocator().getctms(url);
                        binding.setTimeout(60 * 1000);
                        
                        String reltivePath = eventlogs.get(0).getLocalresultfileurl().replace(configReader
                                .getFeedBackLocalPath(), "");
                        String newResultFileURL = configReader.getFtpFeedBackPath()
                                + reltivePath;
                        //若存在状态为2或者4的，则反馈上游失败的SOAP消息
                        if (statusList.contains("2") || statusList.contains("4")) {
                        	log.info("resultNotify fail");
                        	CSPResult result = binding.resultNotify(srccspid, srclspid, correlateid,
                                    1, newResultFileURL);
                            log.info("resultNotify sucess"+result.getResult()+"//"+result.getErrorDescription());
                            receiveEvent.setStatus(ReceiveEventStatus.FAILED_HANDLE);
                        } else {
                        	log.info("resultNotify sucess");
                            //成功时则返回上游成功的SOAP消息
                        	CSPResult result = binding.resultNotify(srccspid, srclspid, correlateid,
                                    0, newResultFileURL);
                            log.info("resultNotify sucess"+result.getResult()+"//"+result.getErrorDescription());
                            receiveEvent.setStatus(ReceiveEventStatus.SUCCESSFULLY_HANDLE);
                        }
                    }
                }catch (Exception ex) {
                	ex.printStackTrace();
                    log.error("resultNotify fail"+ex.getStackTrace());
                    receiveEvent.setStatus(ReceiveEventStatus.FAILED_HANDLE);
                }
                receiveEventService.save(receiveEvent);

            });
        }
    }


    //保存分发日志
    public void saveEpgpubEventlog(ReceiveEvent receiveEvent, String localepgfile) {
        try {
            if(receiveEvent.getReceiveNodeId() != null && receiveEvent.getReceiveNodeId() != 0) {
                ReceiveNode receiveNode = receiveNodeService.findReceiveNodeByReceiveNodeId(receiveEvent.getReceiveNodeId());
                if(receiveNode != null && !StringUtils.isEmpty(receiveNode.getDomainid())) {
                    String []domainStr = receiveNode.getDomainid().split(",");
                    List<Integer> domains = new ArrayList<>();
                    for(String domainid: domainStr) {
                        domains.add(Integer.parseInt(domainid));
                    }
                    //根据接收节点的域查找所有的发布节点（2者的域一致）
                    List<EpgpubNode> epgpubNodes = epgpubNodeService.findEpgpubNodesByDomainidIn(domains);
                    List<EpgpubFile> epgpubFileList = new ArrayList<>();
                    epgpubNodes.forEach(epgpubNode -> {
                        EpgpubFile epgpubFile = new EpgpubFile();
                        File file = new File(configReader.getTarepgwwwroot());
                        if(!file.exists()) file.mkdirs();
                        DownloadUtil.fileCopy(localepgfile, configReader.getTarepgwwwroot()+receiveEvent.getFileName());
                        epgpubFile.setCreateStaffId(configReader.getCreateStaffId());
                        epgpubFile.setAction("1");
                        epgpubFile.setFilename(receiveEvent.getFileName());
                        epgpubFile.setFiletype("2");
                        epgpubFile.setStatus("1");
                        epgpubFile.setCreatetime(new Date());
                        epgpubFile.setDomainid(epgpubNode.getDomainid());
                        epgpubFile.setSourceurl(configReader.getTarepgwwwroot()+receiveEvent.getFileName());
                        epgpubFileList.add(epgpubFile);
                        epgpubFile = epgpubFileService.save(epgpubFile);

                        //生成工单并下发
                        long currentTimeMillis = System.currentTimeMillis();
                        String templateFileLocalPath = configReader.getLocalFilePath() + currentTimeMillis + epgpubFile.getEpgpubfileid()
                                + ".xml";
                        String templateFileFtpPath = configReader.getLocalFilePath() + currentTimeMillis + epgpubFile.getEpgpubfileid()
                                + ".xml";
                        //String correlateID = MD5Util.createCorrelateid();
                        //保存发布日志
                        EpgpubEventlog epgpubeventlog = new EpgpubEventlog();
                        epgpubeventlog.setActivetime(new Date());
                        epgpubeventlog.setCreateTime(new Date());
                        epgpubeventlog.setEpggroupid(JdbcDao.getEpggroupid(receiveEvent.getEpgGroup()));
                        epgpubeventlog.setEpgpubfileid(epgpubFile.getEpgpubfileid());
                        epgpubeventlog.setEpgpubnodeid(epgpubNode.getEpgpubnodeid());
                        if(configReader.isUncompressEpggroupCodeFolderAble()) {
                            epgpubeventlog.setUnzipflag("1");
                        } else {
                            epgpubeventlog.setUnzipflag("0");
                        }
                        epgpubeventlog.setReceiveeventid(receiveEvent.getReceiveEventId());
                        epgpubeventlog.setXmlfile(templateFileFtpPath);
                        epgpubeventlog.setFiletype(epgpubFile.getFiletype());
                        epgpubeventlog.setStatus("0");
                        epgpubeventlog.setDomainid(epgpubNode.getDomainid());
                        epgpubeventlog.setReceiveeventid(receiveEvent.getReceiveEventId());
                        epgpublogService.save(epgpubeventlog);
                        //生成xml工单
                        /*HashMap<String, String> paramsMap = new HashMap<>();
                        paramsMap.put("sourceurl", epgpubFile.getSourceurl());
                        paramsMap.put("ftpRootPath", configReader.getFtpFeedBackPath());
                        paramsMap.put("action", epgpubFile.getAction());
                        if(configReader.isUncompressEpggroupCodeFolderAble()) {
                            paramsMap.put("needUnTar", "1");
                        } else {
                            paramsMap.put("needUnTar", "0");
                        }
                        String activetime = null;
                        if (epgpubeventlog.getActivetime() != null) {
                            SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHHmmss");
                            activetime = smf.format(epgpubeventlog.getActivetime());
                        }
                        paramsMap.put("activetime", activetime);
                        paramsMap.put("epgpageCode", receiveEvent.getEpgGroup());
                        paramsMap.put("epgpubfileid", epgpubFile.getEpgpubfileid() + "");
                        paramsMap.put("destpathurl", epgpubNode.getDestpathurl());
                        paramsMap.put("templateFileLocalPath", templateFileLocalPath);
                        paramsMap.put("epgpubeventlogid", String.valueOf(epgpubeventlog.getEpgpubeventlogid()));
                        try {
                            File templateFile = new File(templateFileLocalPath);
                            templateFile.createNewFile();
                            createEpgpubfileXml(paramsMap);
                        } catch (Exception e) {
                            log.error("createEpgpubfileXml fail", e.getStackTrace());
                        }*/
                    });

                }
            }
        } catch (Exception e) {
            log.error("saveEpgpubeventlog fail"+ e.getStackTrace());
        }

    }


    public void createEpgpubfileXml(Map<String, String> paramsMap) throws Exception {
        log.info("createEpgpubfileXml ");
        String sourceurl = paramsMap.get("sourceurl");
        String ftpRootPath = paramsMap.get("ftpRootPath");
        String epgpageCode = paramsMap.get("epgpageCode");
        String action = paramsMap.get("action");
        String needUnTar = paramsMap.get("needUnTar");
        String activetime = paramsMap.get("activetime");
        String epgpubfileid = paramsMap.get("epgpubfileid");
        String destpathurl = paramsMap.get("destpathurl");
        String templateFileLocalPath = paramsMap.get("templateFileLocalPath");
        String epgpubeventlogid = paramsMap.get("epgpubeventlogid");


        log.info("createEpgpubfileXml sourceurl " + sourceurl + " ftpRootPath IS " + ftpRootPath
                + " templateFileLocalPath is " + templateFileLocalPath);

        Document document = DocumentHelper.createDocument();
        Element rootGen = document.addElement("ADI");
        rootGen.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        Element objectsGen = rootGen.addElement("Objects");
        // add fileset object
        Element filesetGen = objectsGen.addElement("Object");
        filesetGen.addAttribute("ElementType", "EPGFileSet");
        filesetGen.addAttribute("ID", epgpubeventlogid);
        if (epgpageCode != null && !"".equals(epgpageCode)) {
            Element epggroup = filesetGen.addElement("Property");
            epggroup.addAttribute("Name", "EPGGroup");
            if (epgpageCode != null) {
                epggroup.setText(epgpageCode);
            }
        }
        Element systemFile = filesetGen.addElement("Property");
        systemFile.addAttribute("Name", "SystemFile");
        systemFile.setText("0");
        Element NeedUnTar = filesetGen.addElement("Property");
        NeedUnTar.addAttribute("Name", "NeedUnTar");
        if (needUnTar != null) {
            NeedUnTar.setText(needUnTar);
        }
        if (activetime != null && !"".equals(activetime)) {
            Element beginTime = filesetGen.addElement("Property");
            beginTime.addAttribute("Name", "BeginTime");
            beginTime.setText(activetime);
        }
        Element obj = objectsGen.addElement("Object");
        obj.addAttribute("ElementType", "EPGFile");
        obj.addAttribute("ID", epgpubfileid);
        if ("1".equals(action)) {
            obj.addAttribute("Action", "REGIST-UPDATE");
        } else if ("2".equals(action)) {
            obj.addAttribute("Action", "REGIST-DELETE");
        }
        Element sourceUrl = obj.addElement("Property");
        sourceUrl.addAttribute("Name", "SourceUrl");
        //sourceUrl.setText(ftpRootPath + sourceurl.replaceAll(configReader.getFtpLocalPath(), ""));
        Element destPath = obj.addElement("Property");
        destPath.addAttribute("Name", "DestPath");
        if (destpathurl != null) {
            destPath.setText(destpathurl);
        }
        Element md5 = obj.addElement("Property");
        md5.addAttribute("Name", "MD5");
        md5.setText(MD5Util.getHash(sourceurl));
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("UTF-8");
        File file=new File(templateFileLocalPath);
        if(!file.exists()) {
            file.getParentFile().mkdirs();
        }
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file), outputFormat);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();

    }
}
