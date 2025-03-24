package com.liukx.spring.client.annotation.column;

import com.liukx.spring.client.annotation.AVueAttr;
import com.liukx.spring.client.annotation.AVueRule;

import java.lang.annotation.*;

/**
 * 描述: json插件,用的是json-editor-vue插件
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueJson {
    /**
     * 属性名称
     *
     * @return
     */
    String prop() default "";

    /**
     * 文本描述
     *
     * @return
     */
    String label();

    /**
     * 展现类型
     *
     * @return
     */
    String type() default "json";

    /**
     * 对应列的宽度
     */
    String width() default "";

    /**
     * 列是否固定在左侧或者右侧，true 表示固定在左侧 true/left/right
     */
    String fixed() default "";

    /**
     * 最小宽度
     */
    String minWidth() default "";

    /**
     * 对应列是否可以通过拖动改变宽度（需要在option中border属性为真）
     */
    boolean resizable() default true;
    /**
     * 展示模式 : ["tree", "code", "form", "text", "view"]
     *
     * @return
     */
    String currentMode() default "code";

    /**
     * 字段排序
     *
     * @return
     */
    int order() default 0;

    /**
     * 是否单独成行
     *
     * @return
     */
    boolean row() default false;

    /**
     * 是否可以清空选项
     *
     * @return
     */
    boolean clearable() default false;

    /**
     * 是否禁止编辑
     *
     * @return
     */
    boolean disabled() default false;

    /**
     * 文字提示
     *
     * @return
     */
    String tip() default "";

    /**
     * 标题文字提示
     *
     * @return
     */
    String labelTip() default "";

    /**
     * 辅助语: 比如'请选择','请输入'
     *
     * @return
     */
    String placeholder() default "";

    /**
     * 深结构数据绑定取值
     * <p>
     * 比如 : deep.deep.deep.value
     *
     * @return
     */
    String bind() default "";

    /**
     * 是否只读
     * 比如创建时间
     *
     * @return
     */
    boolean readonly() default false;

    /**
     * 是否可见
     *
     * @return
     */
    boolean display() default false;

    /* ********************************** 表单常用属性 ***************************************** */

    /**
     * 比例 12 代表一行的一半，24则铺满整行
     *
     * @return
     */
    int span() default 12;

    /**
     * 表格页面是否展示
     * @return
     */
    boolean hide() default false;

    /**
     * 表单新增时是否禁止
     *
     * @return
     */
    boolean addDisabled() default false;

    /**
     * 表单新增时是否可见
     *
     * @return
     */
    boolean addDisplay() default true;

    /**
     * 表单新增时是否为查看模式
     *
     * @return
     */
    boolean addDetail() default false;

    /**
     * 表单编辑时是否禁止
     *
     * @return
     */
    boolean editDisabled() default false;

    /**
     * 表单编辑时是否可见
     *
     * @return
     */
    boolean editDisplay() default false;

    /**
     * 表单编辑时是否为查看模式
     *
     * @return
     */
    boolean editDetail() default false;

    /**
     * 表单查看时是否可见
     * @return
     */
    boolean viewDisplay() default true;

    /**
     * 表单查看时是否禁止
     * @return
     */
    boolean viewDisabled() default false;

    /**
     * 开启排序
     *
     * @return
     */
    boolean sortable() default false;

    /* ********************************** ********** ******************************************/

    /**
     * 是否作为搜索字段
     *
     * @return
     */
    boolean search() default false;

    /**
     * 拓展字段
     *
     * @return
     */
    AVueAttr[] attrExt() default {};

    /**
     * 校验规则
     *
     * @return
     */
    AVueRule[] rules() default {};

    boolean required() default false;
}
