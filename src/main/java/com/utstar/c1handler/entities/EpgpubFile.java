package com.utstar.c1handler.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "epgpubfile")
@SequenceGenerator(sequenceName = "epgpubfileid", name = "epgpubfileid")
public class EpgpubFile {
    @Id
    /*@SequenceGenerator(sequenceName = "epgpubfileid", name = "epgpubfileid", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epgpubfileid")*/
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "epgpubfileid")
    @GenericGenerator(name = "epgpubfileid", strategy = "com.utstar.c1handler.repositories.CustomIDGenerator")
    @Column(name = "epgpubfileid")
    private Integer epgpubfileid;
    @Column(name = "sourceurl")
    private String sourceurl;
    @Column(name = "action")
    private String action;
    @Column(name = "filetype")
    private String filetype;
    @Column(name = "filename")
    private String filename;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "updatetime")
    private Date updatetime;
    @Column(name = "createstaffid")
    private Integer createStaffId;
    @Column(name = "domainid")
    private Integer domainid;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getEpgpubfileid() {
        return epgpubfileid;
    }

    public void setEpgpubfileid(Integer epgpubfileid) {
        this.epgpubfileid = epgpubfileid;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(Integer createStaffId) {
        this.createStaffId = createStaffId;
    }

    public Integer getDomainid() {
        return domainid;
    }

    public void setDomainid(Integer domainid) {
        this.domainid = domainid;
    }

}
