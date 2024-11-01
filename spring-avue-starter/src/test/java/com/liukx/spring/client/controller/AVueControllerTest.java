package com.liukx.spring.client.controller;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.liukx.spring.client.enums.CheckStatusEnums;
import com.liukx.spring.client.enums.InterestEnums;
import com.liukx.spring.client.enums.SexEnums;
import com.liukx.spring.client.enums.StatusEnums;
import com.liukx.spring.client.model.AVueCrudModel;
import com.liukx.spring.client.model.PropsModel;
import com.liukx.spring.client.utils.JsonParseUtils;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.script.ScriptEngineManager;
import java.util.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class AVueControllerTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    public static final String LIST_URL = "/liukx/list";
    public static final String DIC_URL = "/liukx/dic";
    public static final String UPDATE_URL = "/liukx/update";
    public static final String SAVE_URL = "/liukx/save";
    public static final String BODY_URL = "/test/body";

    @RequestMapping(value = LIST_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE, HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> list(
            @RequestBody
            AVueCrudModel body) {
        logger.info("list request : " + JsonParseUtils.toJson(body));
        ScriptEngineManager manager = new ScriptEngineManager();

        List<AVueCrudModel> crudModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AVueCrudModel crudModel = new AVueCrudModel();
            Map<String, Object> map = new HashMap<>();
            map.put("k" + i, "v" + i);
            crudModel.setDataJson(JsonParseUtils.toJson(map));
            crudModel.setAge(mockInteger(18, 30));
            crudModel.setCheckStatus(JMockData.mock(CheckStatusEnums.class).getCode().toString());
            crudModel.setId(JMockData.mock(Integer.class).toString());
            crudModel.setInterest(Arrays.asList(JMockData.mock(InterestEnums.class).getCode().toString(),
                    JMockData.mock(InterestEnums.class).getCode().toString()));

            crudModel.setLikeStar(mockInteger(1, 4));

            crudModel.setRemoteDic(mockInteger(1, 4) + "");

            crudModel.setSex((Integer) JMockData.mock(SexEnums.class).getCode());
            crudModel.setStatus((Integer) JMockData.mock(StatusEnums.class).getCode());
            crudModel.setValidDate(JMockData.mock(Date.class));
            //            crudModel.setTimeRange(JMockData.mock(Timestamp.class));
            crudModel.setTime(JMockData.mock(Date.class));
            //            crudModel.setDateRange();
            crudModel.setUsername(JMockData.mock(String.class));
            crudModels.add(crudModel);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", 100);
        resultMap.put("pageSize", 10);
        resultMap.put("data", crudModels);
        return ResponseEntity.ok(resultMap);
    }

    @RequestMapping(value = UPDATE_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE,
            HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity update(
            @RequestBody
            AVueCrudModel body) {
        logger.info("update request : " + JsonParseUtils.toJson(body));
        return ResponseEntity.ok(ok(null));
    }

    @RequestMapping(value = SAVE_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE, HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity save(
            @RequestBody
            AVueCrudModel body) {
        logger.info("save request : " + JsonParseUtils.toJson(body));
        return ResponseEntity.ok(ok(null));
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<Map> upload(
            @RequestParam("file")
            MultipartFile file) {
        System.out.println(file);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        Map<String, Object> objResult = new HashMap<>();
        objResult.put("fileName", file.getOriginalFilename());
        objResult.put("url", "https://avuejs.com/imgview/224a5a854632ed8bce5287e9a43b1921.jpg");
        result.put("single", objResult);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = DIC_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE, HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> dicList(
            @RequestBody
            AVueCrudModel body) {
        logger.info("dic request : " + JsonParseUtils.toJson(body));


        List<PropsModel> mockList = new ArrayList<>();
        mockList.add(PropsModel.builder("1", "韩信"));
        mockList.add(PropsModel.builder("2", "李白"));
        mockList.add(PropsModel.builder("3", "王昭君"));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", 100);
        resultMap.put("pageSize", 10);
        resultMap.put("data", mockList);
        return ResponseEntity.ok(resultMap);
    }

    public Map<String, Object> ok(Object obj) {
        return result(true, null, obj);
    }

    private Map<String, Object> result(boolean flag, String message, Object obj) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        resultMap.put("message", message);
        resultMap.put("data", obj);
        return resultMap;
    }

    public Map<String, Object> no(String message) {
        return result(false, message, null);
    }

    @PostMapping(BODY_URL)
    @ResponseBody
    public ResponseEntity<Object> fastBody(@RequestBody Map<String, Object> request) {
        logger.info("后台请求到的数据: " + JsonParseUtils.toJson(request));
        return ResponseEntity.ok(null);
    }

    private Integer mockInteger(int min, int max) {
        MockConfig mockConfig = new MockConfig();
        mockConfig.intRange(min, max);
        return JMockData.mock(Integer.class, mockConfig);
    }

}
