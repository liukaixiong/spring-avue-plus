package com.liukx.spring.client.model;

import com.liukx.spring.client.annotation.AVueConfig;
import com.liukx.spring.client.annotation.AVueCrudOption;
import com.liukx.spring.client.annotation.AVueRouteKey;
import com.liukx.spring.client.annotation.column.AVueInput;
import com.liukx.spring.client.annotation.column.AVueSwitch;
import com.liukx.spring.client.controller.AVueDynamicCacheControllerTest;

/**
 * @author liukaixiong
 * @date 2024/10/11 - 15:03
 */
@AVueRouteKey(groupKey = "dynamicCacheList", title = "动态缓存key维护", description = "负责维护一些缓存的开关，控制某些key是否动态走缓存。")
@AVueCrudOption(title = "动态缓存key维护")
@AVueConfig(list = AVueDynamicCacheControllerTest.LIST_URL, update = AVueDynamicCacheControllerTest.UPDATE_URL, save = AVueDynamicCacheControllerTest.SAVE_URL, successKeyword = "true", successField = "success", messageField = "message")
public class DynamicCacheKeyTemplate {

    @AVueInput(prop = "cacheKey", label = "缓存key", search = true, row = true)
    private String cacheKey;
    @AVueSwitch(prop = "cacheValue", label = "是否开启", row = true, cell = true)
    private boolean cacheValue;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public boolean isCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(boolean cacheValue) {
        this.cacheValue = cacheValue;
    }
}
