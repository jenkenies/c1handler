package com.utstar.c1handler.enums;

import java.util.Objects;

public interface BaseEnum  {
    /**
     * 用于显示枚举名
     * @return 枚举名称
     */
    String getDisplay();

    /**
     * 存储到数据库的枚举值
     * @return
     */
    String getValue();

    static <T extends BaseEnum> T fromValue(Class<T> enumType, String value) {
        for (T object : enumType.getEnumConstants()) {
            if (Objects.equals(value, object.getValue())) {
                return object;
            }
        }
        return null;
    }
}
