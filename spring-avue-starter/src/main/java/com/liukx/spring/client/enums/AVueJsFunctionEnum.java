package com.liukx.spring.client.enums;

/**
 * 默认自带的基础函数
 * 代码在{@code rowClickEvent.js}中
 *
 * @author liukaixiong
 * @date 2024/10/14 - 16:10
 */
public enum AVueJsFunctionEnum {
    /**
     * 必填参数: url
     * {@code @AVueClickButton(methodName = AVueJsFunctionEnum.hrefClick, btnName = "跳转链接", type = "success", icon = "el-icon-setting", attrExt = {
     *                         @AVueAttr(name = "url", value = "https://www.baidu.com")}}
     */
    hrefClick("跳转链接,点击即可跳转到对应的地址!", new String[]{"url : 配置对应的url地址"}),
    /**
     * {@code  @AVueClickButton(methodName = AVueJsFunctionEnum.confirmClickRemoteApi, btnName = "确认按钮", attrExt = {
     *                         @AVueAttr(name = "title", value = "小伙子，你确定吗？有惊喜喔!"),
     *                         @AVueAttr(name = "url", value = AVueControllerTest.BODY_URL)})}
     */
    confirmClickRemoteApi("确认框, 询问之后,触发远程调用,传递的参数是当前行数据", new String[]{"title : 确认内容描述", "url: 请求的地址,当前服务直接传递path即可", "method: 请求方法[get/post]"}),
    openTabLink("点击打开一个弹出层,需要指定弹出层要弹出的模版", new String[]{"title : 确认内容描述", "url: 请求的地址,当前服务直接传递path即可", "method: 请求方法[get/post]"}),
    /**
     * {@code @AVueClickButton(type = "success", btnName = "弹层按钮测试", methodName = AVueJsFunctionEnum.openWindowJsonRemote, attrExt = {
     *                         // 当前弹层的提交路径
     *                         @AVueAttr(name = "submitUrl", value = AVueControllerTest.BODY_URL),
     *                         // 找下一个模版
     *                         @AVueAttr(name = "group", value = "test-config")})}
     */
    openWindowJsonRemote("点击打开一个弹出层,弹出层内渲染一个指定模版,基于当前数据渲染到模版中", new String[]{"group[必填]: 模版编码", "submitUrl [必填]: 弹层中的提交按钮指向的请求地址", "url [非必填]: 模版url,默认是/avue/crud,也就是说本地的模版", "submitEventName 非必填: 提交的方法实现, 在dialogSubmitEvent.js中,默认是defaultSubmit方法", "fieldConvertMap , 字段转换,当前行的数据,要到另一个模版中去,涉及到的值转换"}),

    /**
     * {@code @AVueClickButton(type = "success", btnName = "复制行", methodName = AVueJsFunctionEnum.copyField)}
     * {@code @AVueClickButton(type = "success", btnName = "复制拓展字段", methodName = AVueJsFunctionEnum.copyField, attrExt = {
     *                         @AVueAttr(name = "name", value = "dataJson"),
     *                 }),}
     */
    copyField("复制这一行数据,或者是某个字段的值", new String[]{"字段名称,什么字段都不传默认复制行"}),
    ;


    /**
     * 方法描述
     */
    private final String description;
    /**
     * 参数描述
     */
    private final String[] argsDescription;

    AVueJsFunctionEnum(String description, String[] argsDescription) {
        this.description = description;
        this.argsDescription = argsDescription;
    }

    public String getDescription() {
        return description;
    }

    public String[] getArgsDescription() {
        return argsDescription;
    }
}
