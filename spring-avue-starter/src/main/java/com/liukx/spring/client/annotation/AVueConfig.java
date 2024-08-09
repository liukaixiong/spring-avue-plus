package com.liukx.spring.client.annotation;

import java.lang.annotation.*;

/**
 * 描述: 后端服务路径配置
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueConfig {
    /**
     * 列表的服务路径
     */
    String list();

    /**
     * 保存的服务请求路径
     */
    String save() default "";

    /**
     * 修改的服务路径
     */
    String update() default "";

    /**
     * 删除的服务路径
     */
    String del() default "";

    /**
     * 接口的消息字段
     */
    String messageField() default "";

    /**
     * 接口的标识成功的字段名称
     */
    String successField() default "";

    /**
     * 接口的成功关键字
     */
    String successKeyword() default "";

    AVueAttr[] attrExt() default {};
}
