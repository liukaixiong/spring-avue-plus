package com.liukx.spring.client.controller;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.handler.AVueAnnotationHandler;
import com.liukx.spring.client.helper.JsonAVueCacheHelper;
import com.liukx.spring.client.helper.RouteKeyHelper;
import com.liukx.spring.client.model.ConfigMappingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class AVueController {
    private Logger logger = LoggerFactory.getLogger(AVueController.class);
    public static final String DEFAULT_URL = "/avue/crud";
    public static final String COMPONENT_URL = "/avue/component";
    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    @Autowired
    private AVueAnnotationHandler handler;

    @Autowired
    private AVueProperties vueProperties;

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
            final Map<String, Object> jsonObject = JsonAVueCacheHelper.getINSTANCE().getJsonObject(group, true);
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
        final Map<String, Object> jsonObject = JsonAVueCacheHelper.getINSTANCE().getJsonObject(group, true);
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
        if (StringUtils.isEmpty(configMappingModel.getGroup())) {
            logger.warn(" group is null !");
            return false;
        }
//        if (!StringUtils.isEmpty(vueProperties.getAcceptToken()) && StringUtils
//                .isEmpty(configMappingModel.getAcceptToken())) {
//            logger.warn(" acceptToken is not null!");
//            return false;
//        } else if (!vueProperties.getAcceptToken().equals(configMappingModel.getAcceptToken())) {
//            logger.warn(" acceptToken not match! ");
//            return false;
//        }
        return true;
    }
}
