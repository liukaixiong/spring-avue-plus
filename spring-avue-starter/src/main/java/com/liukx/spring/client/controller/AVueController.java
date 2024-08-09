package com.liukx.spring.client.controller;

import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.handler.AVueAnnotationHandler;
import com.liukx.spring.client.handler.AVueJsonCacheHandler;
import com.liukx.spring.client.helper.RouteKeyHelper;
import com.liukx.spring.client.model.ConfigMappingModel;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class AVueController {
    private final Logger logger = LoggerFactory.getLogger(AVueController.class);
    public static final String DEFAULT_URL = AVueConstants.Path.DEFAULT_URL;
    public static final String COMPONENT_URL = AVueConstants.Path.COMPONENT_URL;
    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    @Autowired
    private AVueAnnotationHandler handler;

    @Resource
    private AVueJsonCacheHandler jsonCacheHandler;

    @GetMapping(value = {AVueConstants.Path.INDEX_CRUD_URL, AVueConstants.Path.INDEX_COMPONENT_URL})
    public String index() {
        return "forward:/avue/index.html";
    }

    @RequestMapping(value = DEFAULT_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE,
            HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> getDocumentation(
            @RequestBody ConfigMappingModel configMappingModel) {

        if (!checkParams(configMappingModel)) {
            return ResponseEntity.badRequest().build();
        }
        // 非空判断
        String group = configMappingModel.getGroup();
        Class clazz = RouteKeyHelper.getInstance().get(group);
        if (clazz == null) {
            final Map<String, Object> jsonObject = jsonCacheHandler.getJsonCache(group);
            if (jsonObject != null) {
                logger.info("从静态缓存中获取AVue模版route-key : " + group);
                return ResponseEntity.ok(jsonObject);
            }
            logger.warn("没有找到AVue对应的模版route-key : " + group);
            return ResponseEntity.notFound().build();
        } else {
            Map<String, Map<String, Object>> parse = handler.parse(clazz);
            return ResponseEntity.ok(parse);
        }
    }


    @RequestMapping(value = COMPONENT_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE,
            HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> componentJson(
            @RequestBody ConfigMappingModel configMappingModel) {

        if (!checkParams(configMappingModel)) {
            return ResponseEntity.badRequest().build();
        }
        // 非空判断
        String group = configMappingModel.getGroup();
        final Map<String, Object> jsonObject = jsonCacheHandler.getJsonCache(group);
        if (jsonObject != null) {
            logger.info("从静态缓存中获取AVue模版route-key : " + group);
            return ResponseEntity.ok(jsonObject);
        }
        logger.warn("没有找到AVue对应的模版route-key : " + group);
        return ResponseEntity.notFound().build();
    }

    /**
     * 参数校验
     *
     * @param configMappingModel
     * @return
     */
    private boolean checkParams(ConfigMappingModel configMappingModel) {
        if (!StringUtils.hasLength(configMappingModel.getGroup())) {
            logger.warn(" group is null !");
            return false;
        }
        return true;
    }
}
