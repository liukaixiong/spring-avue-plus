package com.liukx.spring.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 静态资源访问
 *
 * @author liukaixiong
 * @date 2024/7/29 - 16:13
 */
@Configuration
@EnableWebMvc
public class AVueMvcWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avue/**").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/test").addResourceLocations("classpath:/static/test.html");

//                .resourceChain(false);
    }

    @Bean
    @Order
    @ConditionalOnMissingBean
    public InternalResourceViewResolver internalResourceViewResolver() {
        // 静态转发需要用到
        return new InternalResourceViewResolver();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域问题
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
