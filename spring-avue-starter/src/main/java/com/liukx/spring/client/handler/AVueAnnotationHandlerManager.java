package com.liukx.spring.client.handler;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.helper.RouteKeyHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author liukaixiong
 * @date 2024/11/13 - 11:15
 */
@Component
public class AVueAnnotationHandlerManager implements InitializingBean {

    @Autowired
    private AVueAnnotationHandler handler;

    @Autowired
    private AVueProperties properties;

    private final Map<Class<?>, Map<String, Map<String, Object>>> annotationCache = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!properties.isDebug()) {
            final List<Class<?>> allRouteClass = RouteKeyHelper.getInstance().getAllRouteClass();
            for (Class<?> routeClass : allRouteClass) {
                Map<String, Map<String, Object>> parseMetadata = handler.parse(routeClass);
                annotationCache.put(routeClass, parseMetadata);
            }
        }
    }

    /**
     * 所有注解的解析器管理
     * @param clazz
     * @return
     */
    public Map<String, Map<String, Object>> parse(Class<?> clazz) {
        if (properties.isDebug()) {
            return handler.parse(clazz);
        }
        return annotationCache.get(clazz);
    }

}
