package com.liukx.spring.client.processor;

import com.liukx.spring.client.enums.AVueAttrLevel;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * 属性prop默认值获取
 * 当prop是默认值或者未空字符串时,尝试用field名称作为prop值
 * @author liukaixiong
 * @date 2024/10/16 - 16:25
 */
@Component
public class AVuePropAttrPostProcess implements AVueAttrPostProcess {

    @Override
    public void attrPostProcess(AVueAttrLevel level, AnnotatedElement element, Map<String, Object> attrMap) {
        String PROP_NAME = "prop";
        final Object prop = attrMap.get(PROP_NAME);

        if (prop == null || prop.toString().isEmpty()) {
            if (element instanceof Field) {
                Field field = (Field) element;
                final String name = field.getName();
                attrMap.put(PROP_NAME, name);
            }
        }
    }
}
