package com.utstar.c1handler.enums;

import java.io.Serializable;

public enum ReceiveEventStatus implements Serializable, BaseEnum {
    /**
     * 接收成功
     */
    SUCCESSFULLY_RECEIVE("1", "接收成功"),
    /**
     * 接受失败
     */
    FAILED_RECEIVE("2", "接收失败"),
    /**
     * 处理成功
     */
    SUCCESSFULLY_HANDLE("3", "处理成功"),
    /**
     * 处理失败
     */
    FAILED_HANDLE("4", "处理失败"),

    DOWNLOAD_SUCESS("5", "下载成功"),
    /**
     * 处理失败
     */
    DOWNLOAD_FAIL("6", "下载失败");

    private String value;
    private String display;

    ReceiveEventStatus(String value, String display) {
        this.value = value;
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public String getValue() {
        return value;
    }
}
