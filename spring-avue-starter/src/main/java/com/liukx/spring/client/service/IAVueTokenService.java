package com.liukx.spring.client.service;

import com.liukx.spring.client.model.LoginModel;

/**
 * @author liukaixiong
 * @date 2024/10/15 - 13:33
 */
public interface IAVueTokenService {

    public String tokenCreated(LoginModel loginModel);

    public boolean checkToken(String token);

}
