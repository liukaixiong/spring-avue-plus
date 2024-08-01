package com.liukx.spring.client.processor;

import com.liukx.spring.client.componts.SpringContextHelper;
import com.liukx.spring.client.enums.AVueAttrLevel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedElement;
import java.util.Map;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/9 - 11:41
 */
@Component
public class SpringValueProcess extends AbstractAttrDataProcess implements InitializingBean {

    @Autowired
    private SpringContextHelper contextHelper;

    @Override
    public Object apply(AVueAttrLevel level, AnnotatedElement element, Map<String, Object> attrMap, String attrName) {
        String dicValue = attrMap.get(attrName).toString();
        return contextHelper.parsePropertiesString(dicValue);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addAttrName("dicUrl");
    }
}
