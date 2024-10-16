package com.liukx.spring.client.controller;

import com.github.jsonzou.jmockdata.JMockData;
import com.liukx.spring.client.utils.JsonParseUtils;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public abstract class AVueBaseMockController extends TestCase {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    public static final String LIST_URL = "/list";
    public static final String UPDATE_URL = "/update";
    public static final String SAVE_URL = "/save";

    public abstract Class getTemplateClass();

    @RequestMapping(value = LIST_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE, HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> list(@RequestBody String body) {
        logger.info("list request : " + JsonParseUtils.toJson(body));
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Object mockData = JMockData.mock(getTemplateClass());
            list.add(mockData);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", 100);
        resultMap.put("pageSize", 10);
        resultMap.put("data", list);

        return ResponseEntity.ok(resultMap);
    }

    @RequestMapping(value = UPDATE_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE,
            HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Boolean> update(@RequestBody String body) {
        logger.info("update request : " + JsonParseUtils.toJson(body));
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = SAVE_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE, HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Boolean> save(@RequestBody String body) {
        logger.info("save request : " + JsonParseUtils.toJson(body));
        return ResponseEntity.ok(true);
    }

}
