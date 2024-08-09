package com.liukx.spring.client.handler;

import java.util.Map;

/**
 * 缓存后置处理器
 *
 * @author liukaixiong
 * @date 2024/8/9 - 14:13
 */
public interface AVueJsonCacheProcessor {

    public void post(String key, Map<String, Object> jsonCache);

}
