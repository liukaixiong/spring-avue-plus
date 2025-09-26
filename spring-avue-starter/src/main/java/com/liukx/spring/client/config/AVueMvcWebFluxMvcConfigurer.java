package com.liukx.spring.client.config;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.service.IAVueTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.WebFilter;

import java.util.Set;

/**
 * 静态资源访问
 *
 * @author liukaixiong
 * @date 2024/7/29 - 16:13
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.main.web-application-type", havingValue = "reactive")
//@Import({AVueWebFluxFilterController.class})
public class AVueMvcWebFluxMvcConfigurer implements WebFluxConfigurer {

//    @Autowired
//    private LoginHandler loginHandler;

    @Autowired
    private AVueProperties avueProperties;

    @Autowired
    private IAVueTokenService tokenService;

    @Override
    public void addResourceHandlers(org.springframework.web.reactive.config.ResourceHandlerRegistry registry) {
        registry.addResourceHandler(AVueConstants.Path.INDEX_FULL_URL).addResourceLocations("classpath:/META-INF/avue/");
    }

    @Bean
    public WebFilter staticForwardFilter() {
        return (exchange, chain) -> {
            final String path = exchange.getRequest().getURI().getPath();

            // 静态资源
            if (!path.startsWith(AVueConstants.Path.INDEX_URL) || path.startsWith("/avue/assets")) {
                return chain.filter(exchange);
            }

            final Set<String> aVueMainUrl = Set.of(AVueConstants.Path.INDEX_CRUD_URL, AVueConstants.Path.COMPONENT_ALL_LIST_URL, AVueConstants.Path.INDEX_COMPONENT_URL, AVueConstants.Path.LOGIN_HTML_PATH);
            final Set<String> aVueLoginUrl = Set.of(AVueConstants.Path.LOGIN_HTML_PATH, AVueConstants.Path.DEFAULT_LOGIN_URL, AVueConstants.Path.INDEX_FORWARD_URL);
            final HttpCookie tokenCookie = exchange.getRequest().getCookies().getFirst(AVueConstants.USER_TOKEN_KEY);
            if (avueProperties.isEnableLogin() && !aVueLoginUrl.contains(path) && (tokenCookie == null || !tokenService.checkToken(tokenCookie.getValue()))) {
                ServerHttpResponse response = exchange.getResponse();
                // 设置重定向到登录页面
                response.setStatusCode(HttpStatus.FOUND); // 302 Found
                response.getHeaders().set("Location", AVueConstants.Path.LOGIN_HTML_PATH);

                return response.setComplete(); // 结束响应
            } else if (aVueMainUrl.contains(path)) {
                ServerHttpRequest modifiedRequest = exchange.getRequest()
                        .mutate()
                        .path(AVueConstants.Path.INDEX_FORWARD_URL)
                        .build();
                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public void addCorsMappings(org.springframework.web.reactive.config.CorsRegistry registry) {
        // 跨域问题
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOrigins("*") // 允许跨域请求的域名
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
                .allowedHeaders("*") // 允许的请求头
//                .allowCredentials(true) // 是否允许证书（cookies）
                .maxAge(3600);
    }

}
