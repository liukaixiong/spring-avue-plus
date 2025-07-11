package com.liukx.spring.client.handler.impl;

import com.liukx.spring.client.annotation.AVueGroup;
import com.liukx.spring.client.enums.AVueAttrLevel;
import com.liukx.spring.client.handler.AVueAnnotationHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 动态子表单处理
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/30 - 10:41
 */
public class GroupAnnotationHandler extends DefaultAnnotationHandler {

    @Autowired
    private AVueAnnotationHandler annotationHandler;

    @Override
    @SuppressWarnings(value = {"unchecked"})
    public void handler(AVueAttrLevel level, AnnotatedElement element, Annotation annotation,
        Map<String, Object> levelMap) {

        Class<?> type = ((Field)element).getType();

        Map<String, Object> handler = super.handler(level, element, annotation);

        Map<String, Map<String, Object>> clazzAnnotationMap = annotationHandler.parse(type);
        handler.putAll(clazzAnnotationMap.get("option"));

        List groupList =
            (List)levelMap.computeIfAbsent(AVueAttrLevel.OPTION_GROUP.getKeyName(), k -> new ArrayList<>());

        groupList.add(handler);

    }

    @Override
    public boolean preHandler(AVueAttrLevel level, Annotation element) {
        return element.annotationType().equals(AVueGroup.class);
    }
}
