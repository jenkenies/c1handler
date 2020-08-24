package com.utstar.c1handler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class ConfigReader {
    @Value("${ftpRootPath}")
    private String ftpRootPath;
    @Value("${localFilePath}")
    private String localFilePath;
    @Value("${epgFileUnzipPath}")
    private String epgFileUnzipPath;
    private String cmdPath;
    private String transferCmdPath;
    private String notifyPath;
    private String transferNotifyPath;
    private String epgTemplatePath;
    @Value("${createStaffId}")
    private Integer createStaffId;
    @Value("${uncompressEpggroupCodeFolderAble}")
    private boolean uncompressEpggroupCodeFolderAble;
    @Value("${ftpFeedBackPath}")
    private String ftpFeedBackPath;

    @Value("${tarepgwwwroot}")
    private String tarepgwwwroot;
    @Value("${feedBackLocalPath}")
    private String feedBackLocalPath;

    @Value("${datasource}")
    private String datasource;

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getFeedBackLocalPath() {
        return feedBackLocalPath;
    }

    public void setFeedBackLocalPath(String feedBackLocalPath) {
        this.feedBackLocalPath = feedBackLocalPath;
    }

    public String getTarepgwwwroot() {
        return tarepgwwwroot;
    }

    public void setTarepgwwwroot(String tarepgwwwroot) {
        this.tarepgwwwroot = tarepgwwwroot;
    }


	public String getFtpFeedBackPath() {
		return ftpFeedBackPath;
	}

	public void setFtpFeedBackPath(String ftpFeedBackPath) {
		this.ftpFeedBackPath = ftpFeedBackPath;
	}

	public boolean isUncompressEpggroupCodeFolderAble() {
        return uncompressEpggroupCodeFolderAble;
    }

    public String getFtpRootPath() {
        return ftpRootPath;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public String getEpgFileUnzipPath() {
        return epgFileUnzipPath;
    }

    public String getCmdPath() {
        return cmdPath;
    }

    public String getTransferCmdPath() {
        return transferCmdPath;
    }

    public String getNotifyPath() {
        return notifyPath;
    }

    public String getTransferNotifyPath() {
        return transferNotifyPath;
    }

    public String getEpgTemplatePath() {
        return epgTemplatePath;
    }

    ConfigReader setCmdPath(String cmdPath) {
        this.cmdPath = cmdPath;
        return this;
    }

    ConfigReader setTransferCmdPath(String transferCmdPath) {
        this.transferCmdPath = transferCmdPath;
        return this;
    }

    ConfigReader setNotifyPath(String notifyPath) {
        this.notifyPath = notifyPath;
        return this;
    }

    ConfigReader setTransferNotifyPath(String transferNotifyPath) {
        this.transferNotifyPath = transferNotifyPath;
        return this;
    }

    ConfigReader setEpgTemplatePath(String epgTemplatePath) {
        this.epgTemplatePath = epgTemplatePath;
        return this;
    }

    public Integer getCreateStaffId() {
        return createStaffId;
    }
}