package com.liukx.spring.client.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/18 - 17:41
 */
public class RouteKeyHelper {

    private final Map<String, Class<?>> classMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(RouteKeyHelper.class);
    private static final RouteKeyHelper INSTANCE = new RouteKeyHelper();

    public static RouteKeyHelper getInstance() {
        return INSTANCE;
    }

    public void register(String key, Class<?> clazz) {
        Class<?> oldValue = classMap.put(key, clazz);
        if (oldValue != null) {
            logger.warn("route key 存在重复 : " + key + " , " + clazz.getName() + "\t " + oldValue.getName());
        }
    }

    public List<Class<?>> getAllRouteClass() {
        return new ArrayList<>(classMap.values());
    }

    public Class get(String key) {
        return classMap.get(key);
    }
}
