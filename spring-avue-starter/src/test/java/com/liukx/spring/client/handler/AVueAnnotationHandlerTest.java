package com.liukx.spring.client.handler;

//import com.alibaba.fastjson.JSON;

import com.liukx.spring.client.RuoYiClientTestApplication;
import com.liukx.spring.client.annotation.AVueTableOption;
import com.liukx.spring.client.annotation.EnableAVue;
import com.liukx.spring.client.annotation.column.AVueUpload;
import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.helper.ObjectConfigCacheHelper;
import com.liukx.spring.client.model.*;
import com.liukx.spring.client.utils.JsonParseUtils;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiClientTestApplication.class})
@EnableAVue(basePackages = "com.liukx.spring.client.model")
@EnableConfigurationProperties(AVueProperties.class)
public class AVueAnnotationHandlerTest extends TestCase {

    @Autowired
    private AVueAnnotationHandler annotationHandler;

    @Test
    public void testAVueCrudModel() {
        // 构建当前注解的全局默认属性，可以列中进行属性覆盖。不覆盖的话按照该属性默认处理。
        Map<String, Object> uploadValue = new LinkedHashMap<>();
        uploadValue.put("drag", true);
        ObjectConfigCacheHelper.getINSTANCE().registerAnnotationValue(AVueUpload.class, uploadValue);

        Map<String, Object> optionValue = new LinkedHashMap<>();
        optionValue.put("highlightCurrentRow", true);
        ObjectConfigCacheHelper.getINSTANCE().registerAnnotationValue(AVueTableOption.class, optionValue);

        Map<String, Map<String, Object>> parse = annotationHandler.parse(AVueCrudModel.class);
        System.out.println(JsonParseUtils.toJson(parse));
    }

    @Test
    public void testParse() {
        Map<String, Map<String, Object>> parse = annotationHandler.parse(AVueDynamicModel.class);
        System.out.println(JsonParseUtils.toJson(parse));
    }

    @Test
    public void testAVueGroupModel() {
        Map<String, Map<String, Object>> parse = annotationHandler.parse(AVueGroupModel.class);
        System.out.println(JsonParseUtils.toJson(parse));
    }

    @Test
    public void testJson() {
        PropsModel props = new PropsModel();
        String s = JsonParseUtils.toJson(props);
        System.out.println(s);

        PropsModel parse = JsonParseUtils.parse(s, PropsModel.class);
        System.out.println(parse);
    }

    @Test
    public void testTable(){
        Map<String, Map<String, Object>> parse = annotationHandler.parse(AVueTableModel.class);
        System.out.println(JsonParseUtils.toJson(parse));
    }
}
