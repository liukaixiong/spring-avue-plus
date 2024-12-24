package com.liukx.spring.client.service.impl;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.model.LoginModel;
import com.liukx.spring.client.service.IAVueTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author liukaixiong
 * @date 2024/10/15 - 13:34
 */
public class IAVueMd5TokenServiceImpl implements IAVueTokenService {

    @Autowired
    private AVueProperties avueProperties;

    @Override
    public String tokenCreated(LoginModel loginModel) {
        final String username = avueProperties.getUsername();
        return md5(username);
    }

    @Override
    public boolean checkToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }
        final String username = avueProperties.getUsername();
        final String userToken = md5(username);
        return userToken.equals(token);
    }

    private String md5(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
