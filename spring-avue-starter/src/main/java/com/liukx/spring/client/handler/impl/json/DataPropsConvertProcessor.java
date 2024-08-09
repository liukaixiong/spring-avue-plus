package com.liukx.spring.client.handler.impl.json;

import com.liukx.spring.client.handler.AVueJsonCacheProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据转换编号
 *
 * @author liukaixiong
 * @date 2024/8/9 - 14:16
 */
@Component
public class DataPropsConvertProcessor implements AVueJsonCacheProcessor, InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(DataPropsConvertProcessor.class);

    @Autowired(required = false)
    private List<AVueJsonDataPropsConvert> dataPropsConverts;

    private Map<String, AVueJsonDataPropsConvert> convertMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.convertMap = dataPropsConverts.stream().collect(Collectors.toMap(AVueJsonDataPropsConvert::convertId, var -> var));
    }

    @Override
    public void post(String key, Map<String, Object> jsonCache) {
        if (jsonCache.containsKey("dataConvertId")) {
            final String dataConvertId = jsonCache.get("dataConvertId").toString();
            final AVueJsonDataPropsConvert convert = this.convertMap.get(dataConvertId);
            if (convert != null) {
                convert.convertValue(key, jsonCache);
            } else {
                logger.warn("json cache中没有找到对应的转换id : {}", key);
            }
        }
    }
}
