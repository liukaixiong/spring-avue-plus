package com.liukx.spring.client.model;

/**
 * 自定义属性名称
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/19 - 10:23
 */
public class PropsModel {
    /**
     * label的字段名称
     */
    private String label = "label";

    /**
     * code的字段名称
     */
    private Object value = "value";

    public PropsModel() {
    }

    public static PropsModel builder() {
        return new PropsModel();
    }

    public static PropsModel builder(String codeKey, String labelKey) {
        return new PropsModel(codeKey, labelKey);
    }

    public PropsModel(String codeKey, String labelKey) {
        this.label = labelKey;
        this.value = codeKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
