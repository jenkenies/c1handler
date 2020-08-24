package com.utstar.c1handler.entities;

import com.utstar.c1handler.enums.ReceiveEventStatus;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "receiveevent")
public class ReceiveEvent implements Serializable {
    @Id
    /*@SequenceGenerator(sequenceName = "receiveeventid", name = "receiveeventid", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receiveeventid")*/
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "receiveeventid")
    @GenericGenerator(name = "receiveeventid", strategy = "com.utstar.c1handler.repositories.CustomIDGenerator")
    @Column(name = "receiveeventid")
    private Integer receiveEventId;
    @Column(name = "filename")
    private String fileName;
    @Column(name = "epggroup")
    private String epgGroup;
    @Column(name = "receivenodeid")
    private Integer receiveNodeId;
    @Column(name = "requestxmlfile")
    private String requestXmlFile;
    @Column(name = "description")
    private String description;
    @Column(name = "createstaffid")
    private Integer createStaffId;
    @Column(name = "createdate")
    private Date createDate;
    @Type(type = "com.utstar.c1handler.enums.EnumType")
    private ReceiveEventStatus status;
    @Column(name = "correlateid")
    private String correlateId;
    @Column(name = "cmdfileurl")
    private String cmdfileurl;

    public String getCmdfileurl() {
        return cmdfileurl;
    }

    public void setCmdfileurl(String cmdfileurl) {
        this.cmdfileurl = cmdfileurl;
    }

    public String getCorrelateId() {
        return correlateId;
    }

    public ReceiveEvent setCorrelateId(String correlateId) {
        this.correlateId = correlateId;
        return this;
    }

    public Integer getReceiveEventId() {
        return receiveEventId;
    }

    public ReceiveEvent setReceiveEventId(Integer receiveEventId) {
        this.receiveEventId = receiveEventId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ReceiveEvent setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getEpgGroup() {
        return epgGroup;
    }

    public ReceiveEvent setEpgGroup(String epgGroup) {
        this.epgGroup = epgGroup;
        return this;
    }

    public Integer getReceiveNodeId() {
        return receiveNodeId;
    }

    public ReceiveEvent setReceiveNodeId(Integer receiveNodeId) {
        this.receiveNodeId = receiveNodeId;
        return this;
    }

    public String getRequestXmlFile() {
        return requestXmlFile;
    }

    public ReceiveEvent setRequestXmlFile(String requestXmlFile) {
        this.requestXmlFile = requestXmlFile;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiveEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCreateStaffId() {
        return createStaffId;
    }

    public ReceiveEvent setCreateStaffId(Integer createStaffId) {
        this.createStaffId = createStaffId;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public ReceiveEvent setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public ReceiveEventStatus getStatus() {
        return status;
    }

    public ReceiveEvent setStatus(ReceiveEventStatus status) {
        this.status = status;
        return this;
    }
}