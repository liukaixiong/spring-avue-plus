package com.liukx.spring.client.annotation;

import java.lang.annotation.*;

/**
 * 自定义的事件按钮
 * 详情实现方法参考: rowClickEvent.js
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/10 - 13:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueEventButtons {

    /**
     * 基于行的按钮点击事件
     */
    AVueClickButton[] tableRowButtons() default {};

    /**
     * 基于表格左上方的按钮点击事件
     */
    AVueClickButton[] tableTopLeftButtons() default {};

    /**
     * 基于表格右上方的按钮点击事件
     */
    AVueClickButton[] tableTopRightButtons() default {};

}
