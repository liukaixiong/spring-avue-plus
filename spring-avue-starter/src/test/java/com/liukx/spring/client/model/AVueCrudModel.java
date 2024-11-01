package com.liukx.spring.client.model;

import com.liukx.spring.client.annotation.*;
import com.liukx.spring.client.annotation.column.*;
import com.liukx.spring.client.annotation.column.props.AVueUploadPropsHttp;
import com.liukx.spring.client.controller.AVueControllerTest;
import com.liukx.spring.client.enums.AVueJsFunctionEnum;
import com.liukx.spring.client.enums.CheckStatusEnums;
import com.liukx.spring.client.enums.SexEnums;

import java.util.Date;
import java.util.List;

/**
 * 模拟增删改查表单列表构建数据
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/17 - 13:02
 */
// 模版对应的编号
@AVueRouteKey(groupKey = "test-route", title = "复杂模版路由", description = "这个是用来处理一些比较复杂的模版，里面涵盖了crud，按钮，以及后端的接口路径的定义，包括分页的参数设置等等一系列的demo操作", img = "https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png")
// 表格的标题,整个CRUD的配置,和table渲染相关的
@AVueCrudOption(title = "这是一个复杂的模版", dialogClickModal = true, dialogDrag = true, border = true, viewBtn = true)
// 构建自己的页面自定义数据结构
//@AVuePage(pageData = "data", pageNumber = "pageNo", pageSize = "pSize", pageTotal = "pageTotal")
// 适配后台服务的对应的处理接口
@AVueConfig(list = AVueControllerTest.LIST_URL, update = AVueControllerTest.UPDATE_URL, save = AVueControllerTest.UPDATE_URL, successKeyword = "true", successField = "success", messageField = "message")
// 设置后台接口调用之后成功或者失败的结构模型
@AVueEventButtons(
        // 每一行的按钮及事件定义
        tableRowButtons = {
                // 指定方法名称按钮事件名称
                @AVueClickButton(methodName = AVueJsFunctionEnum.confirmClickRemoteApi, btnName = "确认按钮", attrExt = {
                        @AVueAttr(name = "title", value = "小伙子，你确定吗？有惊喜喔!"),
                        @AVueAttr(name = "url", value = AVueControllerTest.BODY_URL)}),
                // 指定事件
                @AVueClickButton(type = "success", btnName = "弹层按钮测试", methodName = AVueJsFunctionEnum.openWindowJsonRemote, attrExt = {
                        // 当前弹层的提交路径
                        @AVueAttr(name = "submitUrl", value = AVueControllerTest.BODY_URL),
                        // 找下一个模版
                        @AVueAttr(name = "group", value = "test-config"),
                        // 由于不是同一套模版，允许将数据结构进行转换填充。这里指定关系
                        @AVueAttr(name = "fieldConvertMap", value = "dataJson=configJson&&age=validDay")
                }),
                // 指定事件
                @AVueClickButton(type = "success", btnName = "复制拓展字段", methodName = AVueJsFunctionEnum.copyField, attrExt = {
                        // 当前弹层的提交路径
                        @AVueAttr(name = "name", value = "dataJson"),
                }),
                // 指定事件
                @AVueClickButton(type = "success", btnName = "复制行", methodName = AVueJsFunctionEnum.copyField)
        },
        // 左上角按钮事件
        tableTopLeftButtons = {
                @AVueClickButton(methodName = AVueJsFunctionEnum.hrefClick, btnName = "跳转链接", type = "success", icon = "el-icon-setting", attrExt = {
                        @AVueAttr(name = "url", value = "https://www.baidu.com")})
        }
)
public class AVueCrudModel {

    /**
     * 注解介绍
     *
     * @AVueInput: 组件类型以@AVue开始，后面是具体的组件。
     * - input  ： 代表文本框
     * - select ： 代表选择框
     * - number ： 代表数字框
     * - json   ： 代表json组件
     * <p>
     * 属性介绍：
     * addDisplay       ： 表示新增的时候是否展示
     * editDisabled     ： 表示修改的时候是否不可编辑
     * search           ： 代表列表页是否为搜索条件
     * searchRequired   ： 代表搜索条件是否为必填
     * dicData          ： 代表枚举字典
     * dicUrl           ： 代表后台拉取对象
     * 具体属性释义可参考 :  <a href="https://avuejs.com/views/doc.html">...</a>
     * 注解文档参考 : <a href="https://gitee.com/liukaixiong/RuoYi-AVue-Plus/blob/master/doc/annotation.md">...</a>
     */
    @AVueInput(prop = "id", label = "主键", addDisplay = false, editDisabled = true, search = true)
    private String id;

    @AVueInput(prop = "username", label = "用户名称", search = true, searchRequired = true, onClick = "testB")
    private String username;

    // 普通枚举
    @AVueSelect(prop = "checkStatus", label = "认证状态", dicData = "CheckStatusEnums", search = true)
    private String checkStatus = CheckStatusEnums.FOOTBALL.getCode().toString();

    @AVueSelect(prop = "likeStar", label = "喜欢明星", dicData = "test-likeStar-map")
    private Integer likeStar;

    @AVueSelect(label = "远端字典", dicUrl = "http://localhost:8765" + AVueControllerTest.DIC_URL, props = "{'label':'label','value':'value','res':'data'}", dicMethod = "post", dicQuery = "{'key':'key'}", search = true)
    private String remoteDic;
    /**
     * 联动 省市区
     */
    @AVueSelect(label = "省份", cascader = {"city"}, dicUrl = "https://cli.avuejs.com/api/area/getProvince", props = "{'label':'name','value':'code'}", dicMethod = "get", dicQuery = "{'key':'key'}", search = true)
    private String province;
    @AVueSelect(label = "城市", cascader = {"area"}, dicUrl = "https://cli.avuejs.com/api/area/getCity/{{key}}?province={{province}}", props = "{'label':'name','value':'code'}", dicMethod = "get", dicQuery = "{'key':'key'}", search = true)
    private String city;
    @AVueSelect(label = "地区", dicUrl = "https://cli.avuejs.com/api/area/getArea/{{key}}?city={{city}}", props = "{'label':'name','value':'code'}", dicMethod = "get", dicQuery = "{'key':'key'}", search = true)
    private String area;

    @AVueNumber(prop = "age", label = "年龄", labelTip = "这是选择年龄的地方")
    private Integer age = 18;

    @AVueRadio(prop = "sex", label = "性别", border = true, dicData = "SexEnums")
    private int sex = (int) SexEnums.UNKNOWN.getCode();

    @AVueDatetime(prop = "validDate", label = "有效时间")
    private Date validDate;

    @AVueTime(prop = "time", label = "时分秒选择")
    private Date time;

    @AVueTimeRange(prop = "timeRange", label = "时分秒范围", rangeSeparator = "-")
    private List<String> timeRange;

    @AVueDateRange(prop = "dateRange", rangeSeparator = "-", label = "日期范围", searchRange = true, search = true)
    private List<Date> dateRange;


    @AVueCheckbox(prop = "interest", label = "兴趣爱好", dicData = "InterestEnums")
    private List<String> interest;

    @AVueSwitch(prop = "status", label = "状态", dicData = "StatusEnums")
    private int status;

    /**
     * 单图上传
     */
    @AVueUpload(prop = "image", label = "单图上传", listType = "picture-img", action = "/upload", propsHttp = @AVueUploadPropsHttp(res = "single", name = "fileName"))
    private String image;

    /**
     * 多图上传
     */
    @AVueUpload(prop = "imageList", dataType = "array", listType = "picture-card", label = "单图上传", action = "/upload", propsHttp = @AVueUploadPropsHttp(res = "single", name = "fileName"))
    private List<String> imageList;

    /**
     * json组件
     */
    @AVueJson(prop = "dataJson", label = "拓展字段")
    private String dataJson;

    /**
     * 支持模版嵌套
     */
    @AVueDynamic(prop = "simpleModel", label = "子表单测试")
    private AVueNodeModel simpleModel;

    /**
     * 支持分组类型
     */
    @AVueGroup(prop = "groupModel", label = "分组测试1")
    private AVueNodeModel groupModel;

    @AVueGroup(prop = "groupModel2", label = "分组测试2")
    private AVueNodeModel groupModel2;

    public void setRemoteDic(String remoteDic) {
        this.remoteDic = remoteDic;
    }

    public String getRemoteDic() {
        return remoteDic;
    }
    //    public AVueNodeModel getGroupModel() {
    //        return groupModel;
    //    }
    //
    //    public void setGroupModel(AVueNodeModel groupModel) {
    //        this.groupModel = groupModel;
    //    }
    //
    //    public AVueNodeModel getGroupModel2() {
    //        return groupModel2;
    //    }
    //
    //    public void setGroupModel2(AVueNodeModel groupModel2) {
    //        this.groupModel2 = groupModel2;
    //    }
    //
    //    public AVueSimpleModel getSimpleModel() {
    //        return simpleModel;
    //    }
    //
    //    public void setSimpleModel(AVueSimpleModel simpleModel) {
    //        this.simpleModel = simpleModel;
    //    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(List<String> timeRange) {
        this.timeRange = timeRange;
    }

    public List<Date> getDateRange() {
        return dateRange;
    }

    public void setDateRange(List<Date> dateRange) {
        this.dateRange = dateRange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getLikeStar() {
        return likeStar;
    }

    public void setLikeStar(Integer likeStar) {
        this.likeStar = likeStar;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
