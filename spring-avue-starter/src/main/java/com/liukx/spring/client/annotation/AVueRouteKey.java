package com.liukx.spring.client.annotation;

import java.lang.annotation.*;

/**
 * 描述: 选项配置
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueRouteKey {

    /**
     * 路由标记
     */
    String groupKey();

    /**
     * 路由标题
     */
    String title();

    /**
     * 功能详情描述
     */
    String description() default "";

    /**
     * 仅仅只是为了card图片
     */
    String img() default "https://avuejs.com/images/logo.png";

}
