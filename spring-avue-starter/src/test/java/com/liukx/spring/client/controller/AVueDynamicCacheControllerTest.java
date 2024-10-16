package com.liukx.spring.client.controller;

import com.liukx.spring.client.model.DynamicCacheKeyTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.liukx.spring.client.controller.AVueDynamicCacheControllerTest.INDEX_URL;

/**
 * @author liukaixiong
 * @date 2024/10/12 - 10:49
 */
@Controller
@RequestMapping(INDEX_URL)
public class AVueDynamicCacheControllerTest extends AVueBaseMockController {

    public static final String INDEX_URL = "/cache";
    public static final String LIST_URL = INDEX_URL + AVueBaseMockController.LIST_URL;
    public static final String UPDATE_URL = INDEX_URL + AVueBaseMockController.UPDATE_URL;
    public static final String SAVE_URL = INDEX_URL + AVueBaseMockController.SAVE_URL;

    @Override
    public Class getTemplateClass() {
        return DynamicCacheKeyTemplate.class;
    }
}
