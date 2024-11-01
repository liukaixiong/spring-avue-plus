package com.liukx.spring.client.dic;

import com.liukx.spring.client.enums.DicEnumData;

/**
 * 通用的是否枚举
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/19 - 14:33
 */
public enum AVueDicBooleanEnum implements DicEnumData {
    TRUE(1, "是"),
    FALSE(0, "否");

    private final int code;
    private final String label;

    AVueDicBooleanEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Object getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

}
