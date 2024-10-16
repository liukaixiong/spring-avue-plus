package com.liukx.spring.client.annotation;

import java.lang.annotation.*;

/**
 * 描述: 选项配置
 * <a href="https://avuejs.com/crud/crud-page.html">分页参数</a>
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVuePage {

    /**
     * 接口返回数据的根路径,多层级可以用.号分割
     *
     * @return
     */
    String pageRoot() default "";

    /**
     * 分页 - 页码的属性名称
     *
     * @return
     */
    String pageNumber() default "pageNumber";

    /**
     * 分页 - 每页大小的属性名称
     *
     * @return
     */
    String pageSize() default "pageSize";

    /**
     * 分页 - 页总数的名字
     *
     * @return
     */
    String pageTotal() default "total";

    /**
     * 分页 - 数据总数的名称
     *
     * @return
     */
    String pageData() default "data";

    /**
     * 分页 - 页码的属性名称
     *
     * @return
     */
    String total() default "pageNumber";

    /**
     * 页码按钮的数量，当总页数超过该值时会折叠
     *
     * @return
     */
    int pagerCount() default 5;

    /**
     * 每页显示个数选择器的选项设置
     *
     * @return
     */
    int[] pageSizes() default {10, 20, 30, 40, 50, 100};

    /**
     * 是否为分页按钮添加背景色
     *
     * @return
     */
    boolean background() default false;

    /**
     * 组件布局，子组件名用逗号分隔
     * 可选值: sizes, prev, pager, next, jumper, ->, total, slot
     *
     * @return
     */
    String layout() default "";

    /**
     * 回车按键触发提交表单
     *
     * @return
     */
    boolean enter() default false;

    /**
     * 弹出表单的标题的统一宽度
     *
     * @return
     */
    int labelWidth() default 110;

    /**
     * 表单操作菜单的显影
     *
     * @return
     */
    boolean menuBtn() default true;

    /**
     * 表单操作菜单栅格占据的列数
     *
     * @return
     */
    int menuSpan() default 24;

    /**
     * 表单菜单按钮的排列方式
     *
     * @return
     */
    String menuPosition() default "center";

    /**
     * 分组表单,放入正常的column配置就行
     *
     * @return
     */
    String[] group() default {};

    /**
     * 表格总控件大小 [medium/small/mini]
     *
     * @return
     */
    String size() default "small";


    /**
     * 数据为空文案
     *
     * @return
     */
    String emptyText() default "暂无数据";

    AVueAttr[] attrExt() default {};
}
