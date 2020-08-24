/**
 * CtmsSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.utstar.c1handler.webservice.sop;

import com.alibaba.druid.util.StringUtils;
import com.utstar.c1handler.config.ConfigReader;
import com.utstar.c1handler.config.SpringContextUtil;
import com.utstar.c1handler.entities.*;
import com.utstar.c1handler.enums.ReceiveEventStatus;
import com.utstar.c1handler.enums.Status;
import com.utstar.c1handler.service.*;
import com.utstar.c1handler.util.DownloadUtil;
import com.utstar.c1handler.util.TarUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CtmsSoapBindingImpl implements com.utstar.c1handler.webservice.sop.CSPRequest {
	private static final Logger log = LoggerFactory.getLogger(com.utstar.c1handler.webservice.sop.CtmsSoapBindingImpl.class);
	public final static int E_failed = -1;
	public final static int E_Success = 0;
	public final static int E_OtherError = 1;
	public final static int E_DownloadCommandFileFail = 2;
	public final static int E_ParseCommandFileFail = 3;
	public final static int E_DownloadEpgFileFail = 6;


	private final ReceiveNodeService receiveNodeService = SpringContextUtil.getBean(ReceiveNodeService.class);

	private final ReceiveEventService receiveEventService = SpringContextUtil.getBean(ReceiveEventService.class);

	private final EpgpublogService epgpublogService = SpringContextUtil.getBean(EpgpublogService.class);

	private final ConfigReader configReader = SpringContextUtil.getBean(ConfigReader.class);

	private final EpgpubFileService epgpubFileService = SpringContextUtil.getBean(EpgpubFileService.class);

	public com.utstar.c1handler.webservice.sop.CSPResult execCmd(String CSPID,
                                  String LSPID, String correlateID,
                                  String cmdFileURL) throws java.rmi.RemoteException {
		CSPResult result = new CSPResult();
		log.info("I have handled request:cspid:{},lspid:{},correlateid:{},cmdFileUrl:{}", CSPID, LSPID, correlateID, cmdFileURL);
		com.utstar.c1handler.webservice.sop.CSPResult request = new com.utstar.c1handler.webservice.sop.CSPResult();
		List<ReceiveNode> nodeList = receiveNodeService.getByCspAndLspAndStatus(CSPID, LSPID, "1");
		List<ReceiveEvent> receiveEvents = receiveEventService.findReceiveEventByCorrelateId(correlateID);
		if(!CollectionUtils.isEmpty(receiveEvents)) {
			result.setErrorDescription("the correlateID has exist!");
			result.setResult(E_failed);
			log.error("the correlateID has exist!");
			return  result;
		}
        if(CollectionUtils.isEmpty(nodeList)) {
            result.setErrorDescription("CSPID or LSPID are different from expressions.");
            result.setResult(E_failed);
			log.error("CSPID or LSPID are different from expressions.");
            return result;
        }
		List<ReceiveEvent> receiveEventIterable = new ArrayList<>();

		for (ReceiveNode node : nodeList) {
			//内部接口
			if(StringUtils.equals("0", node.getPublishtype())) {
				ReceiveEvent receiveEvent = new ReceiveEvent();
				downloadFile(node, receiveEvent, cmdFileURL, correlateID, "0");
				receiveEventService.save(receiveEvent);
			} else if(StringUtils.equals("1", node.getPublishtype())){
				ReceiveEvent receiveEvent = new ReceiveEvent();
				List<ReceiveEvent> events = receiveEventService.findReceiveEventByCorrelateId(correlateID);
				//相同的节点只存一条接收日志，其他的节点不存接收日志
				if (CollectionUtils.isEmpty(events)) {
					downloadFile(node, receiveEvent, cmdFileURL, correlateID, "1");
					receiveEventService.save(receiveEvent);
				}
			}
		}

		result.setErrorDescription("sucess");
		result.setResult(E_Success);
		return result;
	}



	public com.utstar.c1handler.webservice.sop.CSPResult resultNotify(String CSPID,
                                       String LSPID, String correlateID,
                                       int cmdResult, String resultFileURL)
			throws java.rmi.RemoteException {
		com.utstar.c1handler.webservice.sop.CSPResult result = new com.utstar.c1handler.webservice.sop.CSPResult();
		EpgpubEventlog event = epgpublogService.findEpgpubEventlogByCorrelateid(correlateID);
		if (event == null) {
			result.setResult(E_OtherError);
			result.setErrorDescription("correlateid not found");
			log.error("correlateid not found");
			return result;
		} else {
			EpgpubFile epgpubFile = epgpubFileService.findEpgpubFileByEpgpubfileid(event.getEpgpubfileid());
			epgpubFile.setStatus("2");
			epgpubFileService.save(epgpubFile);
		}

		// download notify file
		String urlfilename = resultFileURL.substring(resultFileURL
				.lastIndexOf("/") + 1);
		String localfile = configReader.getNotifyPath() + File.separator
				+ urlfilename;
		int ret = DownloadUtil.downloadCommandReqFile(resultFileURL, localfile);

		if (ret != E_Success) {
			event.setStatus("4");
			result.setResult(E_OtherError);
			result.setErrorDescription("download notify file fail!");
			log.error("download notify file fail!");
			epgpublogService.save(event);
			return result;
		}

		event.setLocalresultfileurl(localfile);
		String sRet = getNotifyReplyResult(localfile);
		if (sRet == null) {
			event.setStatus("4");
			event.setUpdatetime(new Date());
			event.setResultfileurl(resultFileURL);
			epgpublogService.save(event);
			result.setResult(E_OtherError);
			result.setErrorDescription("parse notify file fail!");
			log.error("parse notify file fail!");
			return result;
		}

		String sTemp[] = sRet.split(",");
		String sResult = sTemp[0];
		String sDesc = sTemp[1];
		log.info("resultNotify correlateID{}, {}, {}", correlateID, sResult, sDesc);
		event.setStatus("3");
		event.setUpdatetime(new Date());
		event.setResultfileurl(resultFileURL);
		epgpublogService.save(event);

		result.setResult(0);
		result.setErrorDescription("success");
		log.info("process resultNotify");
		return result;
	}

	private String getNotifyReplyResult(String resultFile) {
		SAXReader reader = new SAXReader();
		File file = new File(resultFile);
		if (file.exists()) {
			System.out.println(file.getName());
			try {
				Document document = reader.read(file);
				Element root = document.getRootElement();
				String sResult = root.element("Reply").element("Result")
						.getText();
				String sDesc = root.element("Reply").element("Description")
						.getText();

				return sResult + "," + sDesc;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		log.error("get reply File result fail");
		return null;
	}

	//内部接口及时下载tar包，c1接口时由于会超时 定时任务异步去下载tar包
	public com.utstar.c1handler.webservice.sop.CSPResult downloadFile(ReceiveNode receiveNode, ReceiveEvent receiveEvent, String cmdFileURL, String correlateId, String publishType) {
		CSPResult cspResult = new CSPResult();
		String urlfilename = cmdFileURL
				.substring(cmdFileURL.lastIndexOf("/") + 1);
		String localcmdfile = configReader.getLocalFilePath() + File.separator + "cmd_path" + File.separator
				+ urlfilename;
		receiveEvent.setCreateStaffId(configReader.getCreateStaffId());
		receiveEvent.setCreateDate(new Date()).setRequestXmlFile(localcmdfile).setReceiveNodeId(receiveNode.getReceiveNodeId()).setCorrelateId(correlateId);

		// parse workorder request file and get epg template file name
		if (DownloadUtil.downloadCommandReqFile(cmdFileURL, localcmdfile)!=0) {
			cspResult.setResult(E_failed);
			log.error("download xml file fail", cmdFileURL);
			cspResult.setErrorDescription("download xml file fail!");
			receiveEvent.setStatus(ReceiveEventStatus.FAILED_HANDLE);
			return cspResult;
		}
		Document document = getDocument(localcmdfile);
		if (document == null) {
			log.error("parse document fail!", localcmdfile);
			cspResult.setResult(E_failed);
			cspResult.setErrorDescription("parse document fail!");
			receiveEvent.setStatus(ReceiveEventStatus.FAILED_HANDLE);
			return cspResult;
		}
		String tarFileName = getXmlProperty(document, "EPGFile", "SourceUrl");
		if (tarFileName == null) {
			cspResult.setResult(E_failed);
			log.error("parse xml fail!", tarFileName);
			cspResult.setErrorDescription("parse xml fail!");
			receiveEvent.setStatus(ReceiveEventStatus.FAILED_HANDLE);
			return cspResult;
		}
		receiveEvent.setCmdfileurl(tarFileName);
		String epgGroup = getXmlProperty(document, "EPGFileSet", "EPGGroup");
		urlfilename = tarFileName.substring(tarFileName.lastIndexOf("/") + 1);
		receiveEvent.setFileName(urlfilename).setEpgGroup(epgGroup);
		//内部接口时及时下载tar包
		if(StringUtils.equals(publishType, "0")) {
			String localepgfile = configReader.getLocalFilePath() + File.separator + "epg_template_path"
					+ File.separator + urlfilename;
			if (DownloadUtil.downloadCommandReqFile(tarFileName, localepgfile) != 0) {
				cspResult.setResult(E_failed);
				log.error("download epg file fail!", localcmdfile);
				cspResult.setErrorDescription("download epg file fail!");
				receiveEvent.setStatus(ReceiveEventStatus.FAILED_HANDLE);
				return cspResult;
			}
			try {
				String folder = configReader.getEpgFileUnzipPath();
				if (configReader.isUncompressEpggroupCodeFolderAble()) {
					folder = folder + File.separator + epgGroup;
				}
				TarUtil.doDecompress(new File(localepgfile), folder);
			} catch (IOException e) {
				cspResult.setErrorDescription("decompression has failed!");
				cspResult.setResult(-1);
				log.error("decompression fail", e.getStackTrace());
			}
			receiveEvent.setStatus(ReceiveEventStatus.SUCCESSFULLY_HANDLE);
		} else {
			receiveEvent.setStatus(ReceiveEventStatus.SUCCESSFULLY_RECEIVE);
		}
		return cspResult;
	}

	private Document getDocument(String cmdFile) {
		SAXReader reader = new SAXReader();
		File file = new File(cmdFile);
		try {
			return reader.read(file);
		} catch (DocumentException e) {
			log.error("getDocument fail", e.getStackTrace());
		}
		return null;
	}

	private String getXmlProperty(Document document, String elementType, String name) {
		Element rootElement = document.getRootElement();
		List<Element> list = rootElement.element("Objects").elements("Object");
		for (Element element : list) {
			String type = element.attribute("ElementType").getValue();
			if (elementType.equalsIgnoreCase(type)) {
				List<Element> property = element.elements("Property");
				for (Element prop : property) {
					String propName = prop.attribute("Name").getValue();
					if (name.equalsIgnoreCase(propName)) {
						return prop.getText();
					}
				}
			}
		}
		return null;
	}


}
