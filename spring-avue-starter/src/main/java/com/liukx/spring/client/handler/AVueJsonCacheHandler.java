package com.liukx.spring.client.handler;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.helper.JsonAVueCacheHelper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * avue json 缓存处理器
 *
 * @author liukaixiong
 * @date 2024/8/9 - 13:44
 */
@Component
public class AVueJsonCacheHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private AVueProperties vueProperties;

    @Autowired(required = false)
    private List<AVueJsonCacheProcessor> cacheProcessors = new ArrayList<>();

    public Map<String, Object> getJsonCache(String key) {
        if (vueProperties.isDebug()) {
            JsonAVueCacheHelper.getINSTANCE().refreshCache();
        }
        final Map<String, Object> jsonObject = JsonAVueCacheHelper.getINSTANCE().getJsonObject(key);
        if (jsonObject != null) {
            cacheProcessors.forEach(cacheProcessor -> cacheProcessor.post(key, jsonObject));
        } else {
            logger.warn("avue -> 未找到key为{}的json缓存", key);
        }
        return jsonObject;
    }

}
