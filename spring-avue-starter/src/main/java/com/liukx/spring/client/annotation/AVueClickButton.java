package com.liukx.spring.client.annotation;

import com.liukx.spring.client.enums.AVueJsFunctionEnum;

import java.lang.annotation.*;

/**
 * 自定义的事件按钮
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/10 - 13:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueClickButton {

    /**
     * 类型 参考 element-ui -> <a href="https://element-plus.org/zh-CN/component/button.html#button-attributes">点击查看</a>
     * 可选项 : 'primary'| 'success'| 'warning'| 'danger'| 'info'| 'text'(delete)
     */
    String type() default "";

    /**
     * 图标 : <a href="https://element-plus.org/zh-CN/component/icon.html">点击参考</a>
     */
    String icon() default "";

    /**
     * 按钮大小: 'large'| 'default'| 'small'
     */
    String size() default "";

    /**
     * 事件方法
     * 可用方法参考{@link AVueJsFunctionEnum}
     */
    AVueJsFunctionEnum methodName();

    /**
     * 按钮名称
     */
    String btnName();

    /**
     * 该方法触发的字段名称
     */
    String[] fieldNames() default {};

    /**
     * 自定义字段名称,该注解跟随触发事件的方法进行指定
     * 可用方法参考{@link AVueJsFunctionEnum} 中的argsDescription
     */
    AVueAttr[] attrExt() default {};

}
