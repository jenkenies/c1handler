package com.utstar.c1handler.enums;

import java.io.Serializable;

public enum Status implements Serializable, BaseEnum {
    /**
     * 失效
     */
    DISABLE("2","失效")
    ,
    /**
     * 生效
     */
    ENABLE("1","生效");
    private String value;
    private String display;
    Status(String value, String display) {
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
