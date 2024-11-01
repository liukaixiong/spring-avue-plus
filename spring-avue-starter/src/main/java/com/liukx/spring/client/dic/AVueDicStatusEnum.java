package com.liukx.spring.client.dic;

import com.liukx.spring.client.enums.DicEnumData;

/**
 * 通用的有效无效校验
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/19 - 14:33
 */
public enum AVueDicStatusEnum implements DicEnumData {
    TRUE(1, "有效"),
    FALSE(0, "无效");

    private final int code;
    private final String label;

    AVueDicStatusEnum(int code, String label) {
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
