package com.utstar.c1handler.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "epgpubnode")
@SequenceGenerator(sequenceName = "epgpubnodeid", name = "epgpubnodeid")
public class EpgpubNode {
    @Id
    @SequenceGenerator(sequenceName = "epgpubnodeid", name = "epgpubnodeid", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epgpubnodeid")
    @Column(name = "epgpubnodeid")
    private Integer epgpubnodeid;
    @Column(name = "name")
    private String name;
    @Column(name = "interfacetype")
    private String interfacetype;
    @Column(name = "cspid")
    private String cspid;
    @Column(name = "lspid")
    private String lspid;
    @Column(name = "lspname")
    private String lspname;
    @Column(name = "soapurl")
    private String soapurl;
    @Column(name = "destpathurl")
    private String destpathurl;
    @Column(name = "servertype")
    private String servertype;
    @Column(name = "status")
    private String status;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updatetime")
    private Date updatetime;
    @Column(name = "createstaffid")
    private Integer createStaffId;
    @Column(name = "domainid")
    private Integer domainid;

    public Integer getDomainid() {
        return domainid;
    }

    public void setDomainid(Integer domainid) {
        this.domainid = domainid;
    }

    public Integer getEpgpubnodeid() {
        return epgpubnodeid;
    }

    public void setEpgpubnodeid(Integer epgpubnodeid) {
        this.epgpubnodeid = epgpubnodeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfacetype() {
        return interfacetype;
    }

    public void setInterfacetype(String interfacetype) {
        this.interfacetype = interfacetype;
    }

    public String getCspid() {
        return cspid;
    }

    public void setCspid(String cspid) {
        this.cspid = cspid;
    }

    public String getLspid() {
        return lspid;
    }

    public void setLspid(String lspid) {
        this.lspid = lspid;
    }

    public String getLspname() {
        return lspname;
    }

    public void setLspname(String lspname) {
        this.lspname = lspname;
    }

    public String getSoapurl() {
        return soapurl;
    }

    public void setSoapurl(String soapurl) {
        this.soapurl = soapurl;
    }

    public String getDestpathurl() {
        return destpathurl;
    }

    public void setDestpathurl(String destpathurl) {
        this.destpathurl = destpathurl;
    }

    public String getServertype() {
        return servertype;
    }

    public void setServertype(String servertype) {
        this.servertype = servertype;
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

    public Integer getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(Integer createStaffId) {
        this.createStaffId = createStaffId;
    }
}
