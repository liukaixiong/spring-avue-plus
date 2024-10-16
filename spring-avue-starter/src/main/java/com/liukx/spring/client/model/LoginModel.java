package com.liukx.spring.client.model;

/**
 * 登录参数
 * @author liukaixiong
 * @date 2024/10/15 - 11:50
 */

public class LoginModel {

    private String username;

    private String password;


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
}
