package com.utstar.c1handler.entities;

import com.utstar.c1handler.enums.Status;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "c1h_node")
@SequenceGenerator(sequenceName = "c1hNodeId", name = "c1hNodeId")
public class Node {
    @Id
    @Column(name = "c1hnodeid")
    private Integer c1hNodeId;
    @Column(name = "nodename")
    private String nodeName;
    @Column(name = "srccspid")
    private String srcCspId;
    @Column(name = "srclspid")
    private String srcLspId;
    @Column(name = "srcsoapurl")
    private String srcSoapUrl;
    @Column(name = "destsoapurl")
    private String destSoapUrl;
    @Column(name = "destcspid")
    private String destCspId;
    @Column(name = "destlspid")
    private String destLspId;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "lastupdatetime")
    private Date lastUpdateTime;


    public Integer getC1hNodeId() {
        return c1hNodeId;
    }

    public Node setC1hNodeId(Integer c1hNodeId) {
        this.c1hNodeId = c1hNodeId;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Node setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public String getSrcCspId() {
        return srcCspId;
    }

    public Node setSrcCspId(String srcCspId) {
        this.srcCspId = srcCspId;
        return this;
    }

    public String getSrcLspId() {
        return srcLspId;
    }

    public Node setSrcLspId(String srcLspId) {
        this.srcLspId = srcLspId;
        return this;
    }

    public String getSrcSoapUrl() {
        return srcSoapUrl;
    }

    public Node setSrcSoapUrl(String srcSoapUrl) {
        this.srcSoapUrl = srcSoapUrl;
        return this;
    }

    public String getDestSoapUrl() {
        return destSoapUrl;
    }

    public Node setDestSoapUrl(String destSoapUrl) {
        this.destSoapUrl = destSoapUrl;
        return this;
    }

    public String getDestCspId() {
        return destCspId;
    }

    public Node setDestCspId(String destCspId) {
        this.destCspId = destCspId;
        return this;
    }

    public String getDestLspId() {
        return destLspId;
    }

    public Node setDestLspId(String destLspId) {
        this.destLspId = destLspId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Node setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public Node setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }
}
