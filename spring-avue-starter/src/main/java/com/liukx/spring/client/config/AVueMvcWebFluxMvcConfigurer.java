package com.liukx.spring.client.config;

import com.liukx.spring.client.enums.AVueConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
public class AVueMvcWebFluxMvcConfigurer implements WebFluxConfigurer {

//    @Autowired
//    private LoginHandler loginHandler;


    @Override
    public void addResourceHandlers(org.springframework.web.reactive.config.ResourceHandlerRegistry registry) {
        registry.addResourceHandler(AVueConstants.Path.INDEX_FULL_URL).addResourceLocations("classpath:/META-INF/avue/");
    }

    @Bean
    public WebFilter staticForwardFilter() {
        return (exchange, chain) -> {
            final Set<String> aVueMainUrl = Set.of(AVueConstants.Path.INDEX_CRUD_URL, AVueConstants.Path.COMPONENT_ALL_LIST_URL, AVueConstants.Path.INDEX_COMPONENT_URL, AVueConstants.Path.LOGIN_HTML_PATH);
            if (aVueMainUrl.contains(exchange.getRequest().getURI().getPath())) {
                ServerHttpRequest modifiedRequest = exchange.getRequest()
                        .mutate()
                        .path("/avue/index.html")
                        .build();
                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            }
            return chain.filter(exchange);
        };
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginHandler).addPathPatterns(AVueConstants.Path.INDEX_FULL_URL)
//                .excludePathPatterns(AVueConstants.Path.LOGIN_HTML_PATH, AVueConstants.Path.DEFAULT_LOGIN_URL, AVueConstants.Path.INDEX_FORWARD_URL)
//                .excludePathPatterns("/avue/assets/**");
//    }

    //    @Bean
//    public WebFilter loginFilter() {
//        return (exchange, chain) -> {
//            // 在这里添加你的拦截逻辑
//            // 例如：检查登录状态
//            // 可以使用 exchange.getRequest().getURI().getPath() 来获取请求路径
//            String path = exchange.getRequest().getURI().getPath();
//            if (path.equals(AVueConstants.Path.INDEX_FULL_URL)) {
//                // 检查是否需要拦截
//                if (shouldExclude(path)) {
//                    return chain.filter(exchange);
//                }
//                // 执行拦截逻辑
//            }
//            // 例如：检查登录状态
//            return chain.filter(exchange);
//        };
//    }
    private boolean shouldExclude(String path) {
        return path.equals(AVueConstants.Path.LOGIN_HTML_PATH) ||
                path.equals(AVueConstants.Path.DEFAULT_LOGIN_URL) ||
                path.equals(AVueConstants.Path.INDEX_FORWARD_URL) ||
                path.startsWith("/avue/assets/");
    }

//    @Bean
//    @Order
//    @ConditionalOnMissingBean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        // 静态转发需要用到
//        return new InternalResourceViewResolver();
//    }


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
