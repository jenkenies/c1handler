package com.utstar.c1handler.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "C1H_WORKORDEREVENT")
public class WorkOrderEvent {
    @Id
    @Column(name = "c1hworkordereventid")
    @SequenceGenerator(sequenceName = "c1hworkordereventid", name = "c1hworkordereventid", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "c1hworkordereventid")
    private Integer c1hWorkOrderEventId;
    @Column(name = "correlateid")
    private String correlateId;
    @Column(name = "cspid")
    private String cspId;
    @Column(name = "lspid")
    private String lspId;
    @Column(name = "cmdfileurl")
    private String cmdFileUrl;
    @Column(name = "c1hnodeid")
    private Integer c1hNodeId;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "dispatchtime")
    private Date dispatchTime;
    @Column(name = "result")
    private String result;
    @Column(name = "errdescription")
    private String errDescription;
    @Column(name = "notifytime")
    private Date notifyTime;
    @Column(name = "cmdresult")
    private String cmdResult;
    @Column(name = "resultfileurl")
    private String resultFileUrl;
    @Column(name = "cmderrdesc")
    private String cmdErrDesc;
    @Column(name = "comments")
    private String comments;
    @Column(name = "localcmdfileurl")
    private String localCmdFileUrl;
    @Column(name = "localresultfileurl")
    private String localResultFileUrl;
    @Column(name = "dispatchresulttime")
    private Date dispatchResultTime;


    public Integer getC1hNodeId() {
        return c1hNodeId;
    }

    public WorkOrderEvent setC1hNodeId(Integer c1hNodeId) {
        this.c1hNodeId = c1hNodeId;
        return this;
    }

    /**
     * 老代码已无法得知枚举具体含义了,所以暂定String类型.
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public WorkOrderEvent setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getC1hWorkOrderEventId() {
        return c1hWorkOrderEventId;
    }

    public WorkOrderEvent setC1hWorkOrderEventId(Integer c1hWorkOrderEventId) {
        this.c1hWorkOrderEventId = c1hWorkOrderEventId;
        return this;
    }

    public String getCorrelateId() {
        return correlateId;
    }

    public WorkOrderEvent setCorrelateId(String correlateId) {
        this.correlateId = correlateId;
        return this;
    }

    public String getCspId() {
        return cspId;
    }

    public WorkOrderEvent setCspId(String cspId) {
        this.cspId = cspId;
        return this;
    }

    public String getLspId() {
        return lspId;
    }

    public WorkOrderEvent setLspId(String lspId) {
        this.lspId = lspId;
        return this;
    }

    public String getCmdFileUrl() {
        return cmdFileUrl;
    }

    public WorkOrderEvent setCmdFileUrl(String cmdFileUrl) {
        this.cmdFileUrl = cmdFileUrl;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public WorkOrderEvent setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public WorkOrderEvent setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
        return this;
    }

    public String getResult() {
        return result;
    }

    public WorkOrderEvent setResult(String result) {
        this.result = result;
        return this;
    }

    public String getErrDescription() {
        return errDescription;
    }

    public WorkOrderEvent setErrDescription(String errDescription) {
        this.errDescription = errDescription;
        return this;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public WorkOrderEvent setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
        return this;
    }

    public String getCmdResult() {
        return cmdResult;
    }

    public WorkOrderEvent setCmdResult(String cmdResult) {
        this.cmdResult = cmdResult;
        return this;
    }

    public String getResultFileUrl() {
        return resultFileUrl;
    }

    public WorkOrderEvent setResultFileUrl(String resultFileUrl) {
        this.resultFileUrl = resultFileUrl;
        return this;
    }

    public String getCmdErrDesc() {
        return cmdErrDesc;
    }

    public WorkOrderEvent setCmdErrDesc(String cmdErrDesc) {
        this.cmdErrDesc = cmdErrDesc;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public WorkOrderEvent setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public String getLocalCmdFileUrl() {
        return localCmdFileUrl;
    }

    public WorkOrderEvent setLocalCmdFileUrl(String localCmdFileUrl) {
        this.localCmdFileUrl = localCmdFileUrl;
        return this;
    }

    public String getLocalResultFileUrl() {
        return localResultFileUrl;
    }

    public WorkOrderEvent setLocalResultFileUrl(String localResultFileUrl) {
        this.localResultFileUrl = localResultFileUrl;
        return this;
    }

    public Date getDispatchResultTime() {
        return dispatchResultTime;
    }

    public WorkOrderEvent setDispatchResultTime(Date dispatchResultTime) {
        this.dispatchResultTime = dispatchResultTime;
        return this;
    }
}
