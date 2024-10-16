package com.liukx.spring.client.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * AVue客户端的可配置项
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/18 - 16:55
 */
@ConfigurationProperties(prefix = "spring.avue")
public class AVueProperties {
    /**
     * 是否显示默认值 有bug,前端存在不兼容,有一些特殊属性会引起冲突
     */
    @Deprecated
    private boolean showDefaultValue = false;

    /**
     * 是否开启开发模式， 该模式不会对数据进行缓存,但是每次都会重新构建，方便开发调试。生产请关闭！
     */
    private boolean debug = false;

    /**
     * 授权访问的token
     */
    private String acceptToken;

    /**
     * 是否启用登录
     */
    private boolean enableLogin;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public boolean isEnableLogin() {
        return enableLogin;
    }

    public void setEnableLogin(boolean enableLogin) {
        this.enableLogin = enableLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAcceptToken() {
        return acceptToken;
    }

    public void setAcceptToken(String acceptToken) {
        this.acceptToken = acceptToken;
    }

    public boolean isShowDefaultValue() {
        return showDefaultValue;
    }

    public void setShowDefaultValue(boolean showDefaultValue) {
        this.showDefaultValue = showDefaultValue;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
