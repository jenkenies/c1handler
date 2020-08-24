package com.utstar.c1handler.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "epgpubeventlog")
@SequenceGenerator(sequenceName = "epgpubeventlogid", name = "epgpubeventlogid")
public class EpgpubEventlog {
    @Id
    /*@SequenceGenerator(sequenceName = "epgpubeventlogid", name = "epgpubeventlogid", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epgpubeventlogid")*/
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "epgpubeventlogid")
    @GenericGenerator(name = "epgpubeventlogid", strategy = "com.utstar.c1handler.repositories.CustomIDGenerator")
    @Column(name = "epgpubeventlogid")
    private Integer epgpubeventlogid;
    @Column(name = "epgpubfileid")
    private Integer epgpubfileid;
    @Column(name = "epggroupid")
    private Integer epggroupid;
    @Column(name = "filetype")
    private String filetype;
    @Column(name = "epgpubnodeid")
    private Integer epgpubnodeid;
    @Column(name = "correlateid")
    private String correlateid;
    @Column(name = "proccode")
    private String proccode;
    @Column(name = "unzipflag")
    private String unzipflag;
    @Column(name = "activetime")
    private Date activetime;
    @Column(name = "xmlfile")
    private String xmlfile;
    @Column(name = "status")
    private String status;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updatetime")
    private Date updatetime;
    @Column(name = "domainid")
    private Integer domainid;
    @Column(name = "receiveeventid")
    private Integer receiveeventid;
    @Column(name = "resultfileurl")
    private String resultfileurl;
    @Column(name = "localresultfileurl")
    private String localresultfileurl;

    public String getResultfileurl() {
        return resultfileurl;
    }

    public void setResultfileurl(String resultfileurl) {
        this.resultfileurl = resultfileurl;
    }

    public String getLocalresultfileurl() {
        return localresultfileurl;
    }

    public void setLocalresultfileurl(String localresultfileurl) {
        this.localresultfileurl = localresultfileurl;
    }

    public Integer getReceiveeventid() {
        return receiveeventid;
    }

    public void setReceiveeventid(Integer receiveeventid) {
        this.receiveeventid = receiveeventid;
    }

    public Integer getEpgpubeventlogid() {
        return epgpubeventlogid;
    }

    public void setEpgpubeventlogid(Integer epgpubeventlogid) {
        this.epgpubeventlogid = epgpubeventlogid;
    }

    public Integer getEpgpubfileid() {
        return epgpubfileid;
    }

    public void setEpgpubfileid(Integer epgpubfileid) {
        this.epgpubfileid = epgpubfileid;
    }

    public Integer getEpggroupid() {
        return epggroupid;
    }

    public void setEpggroupid(Integer epggroupid) {
        this.epggroupid = epggroupid;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Integer getEpgpubnodeid() {
        return epgpubnodeid;
    }

    public void setEpgpubnodeid(Integer epgpubnodeid) {
        this.epgpubnodeid = epgpubnodeid;
    }

    public String getCorrelateid() {
        return correlateid;
    }

    public void setCorrelateid(String correlateid) {
        this.correlateid = correlateid;
    }

    public String getProccode() {
        return proccode;
    }

    public void setProccode(String proccode) {
        this.proccode = proccode;
    }

    public String getUnzipflag() {
        return unzipflag;
    }

    public void setUnzipflag(String unzipflag) {
        this.unzipflag = unzipflag;
    }

    public Date getActivetime() {
        return activetime;
    }

    public void setActivetime(Date activetime) {
        this.activetime = activetime;
    }

    public String getXmlfile() {
        return xmlfile;
    }

    public void setXmlfile(String xmlfile) {
        this.xmlfile = xmlfile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDomainid() {
        return domainid;
    }

    public void setDomainid(Integer domainid) {
        this.domainid = domainid;
    }
}
