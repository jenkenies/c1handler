package com.utstar.c1handler.entities;

import com.utstar.c1handler.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "receivenode")
public class ReceiveNode implements Serializable {
    @Id
    @Column(name = "receivenodeid")
    @SequenceGenerator(sequenceName = "receivenodeid", name = "receivenodeid", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receivenodeid")
    private Integer receiveNodeId;
    @Column(name = "name")
    private String name;
    @Column(name = "lsp")
    private String lsp;
    @Column(name = "csp")
    private String csp;
    @Column(name = "url")
    private String url;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "createstaffid")
    private Integer createStaffId;
    @Column(name = "createdate")
    private Date createDate;
    @Column(name = "publishtype")
    private String publishtype;
    @Column(name = "domainid")
    private String domainid;

    @Column(name = "srccsp")
    private String srccsp;
    @Column(name = "srclsp")
    private String srclsp;
    @Column(name = "srcurl")
    private String srcurl;

    public String getSrccsp() {
        return srccsp;
    }

    public void setSrccsp(String srccsp) {
        this.srccsp = srccsp;
    }

    public String getSrclsp() {
        return srclsp;
    }

    public void setSrclsp(String srclsp) {
        this.srclsp = srclsp;
    }

    public String getSrcurl() {
        return srcurl;
    }

    public void setSrcurl(String srcurl) {
        this.srcurl = srcurl;
    }

    public String getPublishtype() {
        return publishtype;
    }

    public void setPublishtype(String publishtype) {
        this.publishtype = publishtype;
    }

    public String getDomainid() {
        return domainid;
    }

    public void setDomainid(String domainid) {
        this.domainid = domainid;
    }

    public Integer getReceiveNodeId() {
        return receiveNodeId;
    }

    public ReceiveNode setReceiveNodeId(Integer receiveNodeId) {
        this.receiveNodeId = receiveNodeId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReceiveNode setName(String name) {
        this.name = name;
        return this;
    }

    public String getLsp() {
        return lsp;
    }

    public ReceiveNode setLsp(String lsp) {
        this.lsp = lsp;
        return this;
    }

    public String getCsp() {
        return csp;
    }

    public ReceiveNode setCsp(String csp) {
        this.csp = csp;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ReceiveNode setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ReceiveNode setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiveNode setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCreateStaffId() {
        return createStaffId;
    }

    public ReceiveNode setCreateStaffId(Integer createStaffId) {
        this.createStaffId = createStaffId;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public ReceiveNode setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }
}