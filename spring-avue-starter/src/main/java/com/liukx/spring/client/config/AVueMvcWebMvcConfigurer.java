package com.liukx.spring.client.config;

import com.liukx.spring.client.controller.AVueWebMvcController;
import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 静态资源访问
 *
 * @author liukaixiong
 * @date 2024/7/29 - 16:13
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(value = {WebMvcConfigurer.class})
@ConditionalOnProperty(name = "spring.main.web-application-type", havingValue = "servlet", matchIfMissing = true)
@Import({AVueWebMvcController.class})
public class AVueMvcWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginHandler loginHandler;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(AVueConstants.Path.INDEX_FULL_URL).addResourceLocations("classpath:/META-INF/avue/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandler).addPathPatterns(AVueConstants.Path.INDEX_FULL_URL)
                .excludePathPatterns(AVueConstants.Path.LOGIN_HTML_PATH, AVueConstants.Path.DEFAULT_LOGIN_URL, AVueConstants.Path.INDEX_FORWARD_URL)
                .excludePathPatterns("/avue/assets/**");
    }

    @Bean
    @Order
    @ConditionalOnMissingBean
    public InternalResourceViewResolver internalResourceViewResolver() {
        // 静态转发需要用到
        return new InternalResourceViewResolver();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.main.web-application-type", havingValue = "servlet", matchIfMissing = true)
    @ConditionalOnClass(value = {HandlerInterceptor.class})
    public LoginHandler avueLoginHandler() {
        return new LoginHandler();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域问题
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOrigins("*") // 允许跨域请求的域名
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
                .allowedHeaders("*") // 允许的请求头
//                .allowCredentials(true) // 是否允许证书（cookies）
                .maxAge(3600);
    }


}
