package com.liukx.spring.client.model;

import com.liukx.spring.client.annotation.AVueConfig;
import com.liukx.spring.client.annotation.AVueCrudOption;
import com.liukx.spring.client.annotation.AVueRouteKey;
import com.liukx.spring.client.annotation.AVueRule;
import com.liukx.spring.client.annotation.column.*;
import com.liukx.spring.client.controller.AVueConfigControllerTest;

/**
 * 模拟表单列表构建数据
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/17 - 13:02
 * //
 */
@AVueRouteKey(groupKey = "test-config", title = "简单的模版路由", description = "这是一个比较简单的测试，主要是用来展示简单的增删改查需要用的一些简单的参数")
@AVueCrudOption(title = "这是一个测试")
@AVueConfig(list = AVueConfigControllerTest.LIST_URL, update = AVueConfigControllerTest.UPDATE_URL, save = AVueConfigControllerTest.UPDATE_URL, successKeyword = "true", successField = "success", messageField = "message")
public class AVueSimpleModel {

    @AVueInput(prop = "id", label = "主键", addDisplay = false, row = true, editDisabled = true, search = true)
    private String id;

    @AVueInput(prop = "configGroup", label = "组名称", search = true, row = true, rules = {
            @AVueRule(required = true, message = "组名称要填咧"), @AVueRule(min = 5, max = 10, message = "我跟你讲最小5个,最大10个.")})
    private String configGroup;

    @AVueInput(prop = "configName", label = "配置名称", search = true, required = true, row = true)
    private String configName;

    @AVueSelect(prop = "configCode", label = "配置值", dicData = "StatusEnums", search = true, required = true, row = true)
    private String configCode;

    @AVueNumber(prop = "validDay", label = "有效天数", search = true, required = true, row = true)
    private Integer validDay;

    @AVueSwitch(prop = "status", label = "状态", dicData = "StatusEnums", row = true)
    private int status;

    @AVueJson(label = "配置json")
    private String configJson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigGroup() {
        return configGroup;
    }

    public void setConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getValidDay() {
        return validDay;
    }

    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }
}
