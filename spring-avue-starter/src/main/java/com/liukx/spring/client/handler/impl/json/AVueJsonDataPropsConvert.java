package com.liukx.spring.client.handler.impl.json;

import java.util.Map;

/**
 * @author liukaixiong
 * @date 2024/8/9 - 14:18
 */
public interface AVueJsonDataPropsConvert {

    public String convertId();

    public void convertValue(String key, Map<String, Object> jsonCache);

}
