package com.liukx.spring.client.processor;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.enums.AVueAttrLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/16 - 13:41
 */
@Component
public class DefaultFieldValueProcess implements AVueAttrPostProcess {
    private final Logger logger = LoggerFactory.getLogger(DefaultFieldValueProcess.class);
    /**
     * 对象属性缓存
     */
    private final Map<String, Object> objectFieldMap = new HashMap<>(64);

    @Autowired
    private AVueProperties prop;

    private final Object nullObject = new Object();

    @Override
    public void attrPostProcess(AVueAttrLevel level, AnnotatedElement element, Map<String, Object> attrMap) {
        if (level == AVueAttrLevel.OPTION_COLUMN) {
            Object classDefaultValue = getAttrDefaultValueCache((Field) element);
            if (classDefaultValue != null) {
                attrMap.put("value", classDefaultValue);
            }
        }
    }

    private Object getAttrDefaultValueCache(Field element) {
        Class<?> declaringClass = element.getDeclaringClass();
        String simpleName = declaringClass.getSimpleName();
        String name = element.getName();

        if (prop.isDebug()) {
            return getClazzDefaultValue(declaringClass, element);
        }

        final Object clazzDefaultValue = getClazzDefaultValue(declaringClass, element);

        Object value =
                objectFieldMap.computeIfAbsent(simpleName + "-" + name, k -> clazzDefaultValue);

        if (value != null && value != nullObject) {
            return value;
        }
        return null;
    }

    private Object getClazzDefaultValue(Class<?> declaringClass, Field element) {
        Object clazzObject = null;
        if (prop.isDebug()) {
            clazzObject = newObject(declaringClass);
        } else {
            clazzObject = objectFieldMap.computeIfAbsent(declaringClass.getSimpleName(), k -> newObject(declaringClass));
        }

        if (clazzObject == null || nullObject == clazzObject) {
            return nullObject;
        }

        try {
            element.setAccessible(true);
            return element.get(clazzObject);
        } catch (Exception e) {
            logger.error("new error", e);
        }
        return null;
    }

    private Object newObject(Class<?> declaringClass) {
        try {
            return declaringClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.error("new error", e);
        }
        return nullObject;
    }

}
