package com.liukx.spring.client.processor;

import com.liukx.spring.client.componts.SpringContextHelper;
import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.enums.AVueAttrLevel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    @Autowired
    private AVueProperties avueProperties;

    @Override
    public Object apply(AVueAttrLevel level, AnnotatedElement element, Map<String, Object> attrMap, String attrName) {
        String dicValue = attrMap.get(attrName).toString();
        final String url = contextHelper.parsePropertiesString(dicValue);
        final String webDomain = avueProperties.getWebDomain();
        if (StringUtils.hasText(webDomain) && !url.startsWith("http")) {
            return webDomain + url;
        }
        return url;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addAttrName("dicUrl");
        addAttrName("action");
    }
}
