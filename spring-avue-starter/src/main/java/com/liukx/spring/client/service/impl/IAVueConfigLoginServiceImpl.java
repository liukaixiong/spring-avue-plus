package com.liukx.spring.client.service.impl;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.model.LoginModel;
import com.liukx.spring.client.service.IAVueLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author liukaixiong
 * @date 2024/10/15 - 13:26
 */
public class IAVueConfigLoginServiceImpl implements IAVueLoginService {

    @Autowired
    private AVueProperties avueProperties;

    @Override
    public boolean login(LoginModel loginModel) {
        final String username = avueProperties.getUsername();
        final String password = avueProperties.getPassword();

        if (!(StringUtils.hasText(username) && StringUtils.hasText(password))) {
            return false;
        }

        return username.equals(loginModel.getUsername()) && password.equals(loginModel.getPassword());
    }
}
