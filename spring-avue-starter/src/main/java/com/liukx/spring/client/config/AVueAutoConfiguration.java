package com.liukx.spring.client.config;

import com.liukx.spring.client.annotation.AVueGroup;
import com.liukx.spring.client.annotation.column.AVueDynamic;
import com.liukx.spring.client.annotation.column.AVueJson;
import com.liukx.spring.client.annotation.column.AVueTable;
import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.handler.LoginHandler;
import com.liukx.spring.client.handler.impl.*;
import com.liukx.spring.client.service.IAVueLoginService;
import com.liukx.spring.client.service.IAVueTokenService;
import com.liukx.spring.client.service.impl.IAVueConfigLoginServiceImpl;
import com.liukx.spring.client.service.impl.IAVueMd5TokenServiceImpl;
import com.liukx.spring.client.utils.SpringContextUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * AVue使用自动化配置
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/18 - 16:56
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AVueProperties.class)
@ComponentScan(basePackages = {"com.liukx.spring.client"})
@Import({SpringContextUtil.class})
public class AVueAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(value = DefaultAnnotationHandler.class)
    public DefaultAnnotationHandler defaultAnnotationHandler() {
        DefaultAnnotationHandler defaultAnnotationHandler = new DefaultAnnotationHandler();
        defaultAnnotationHandler.addExcludeAnnotationSet(AVueGroup.class);
        return defaultAnnotationHandler;
    }

    @Bean
    @ConditionalOnMissingBean(value = SupportNestingAnnotationHandler.class)
    public SupportNestingAnnotationHandler dynamicAnnotationHandler() {
        SupportNestingAnnotationHandler supportNestingAnnotationHandler = new SupportNestingAnnotationHandler();
        supportNestingAnnotationHandler.addNestingAnnotation(AVueDynamic.class);
        supportNestingAnnotationHandler.addNestingAnnotation(AVueTable.class);
        return supportNestingAnnotationHandler;
    }


    @Bean
    @ConditionalOnMissingBean(value = GroupAnnotationHandler.class)
    public GroupAnnotationHandler groupAnnotationHandler() {
        return new GroupAnnotationHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = DefaultAnnotationValueHandler.class)
    public DefaultAnnotationValueHandler defaultAnnotationValueHandler() {
        return new DefaultAnnotationValueHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = NewImportComponentsHandler.class)
    public NewImportComponentsHandler newImportComponentsHandler() {
        // 新注册的组件处理
        NewImportComponentsHandler newImportComponentsHandler = new NewImportComponentsHandler();
        newImportComponentsHandler.addRegisterComponent(AVueJson.class);
        newImportComponentsHandler.addRegisterComponent(AVueTable.class);
        return newImportComponentsHandler;
    }

    @Bean
    @ConditionalOnMissingBean(value = IAVueLoginService.class)
    public IAVueLoginService aVueConfigLoginServiceImpl() {
        return new IAVueConfigLoginServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(value = IAVueTokenService.class)
    public IAVueTokenService aVueMd5TokenServiceImpl() {
        return new IAVueMd5TokenServiceImpl();
    }


}
