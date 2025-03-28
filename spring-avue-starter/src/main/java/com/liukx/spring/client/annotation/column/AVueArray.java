package com.liukx.spring.client.annotation.column;

import com.liukx.spring.client.annotation.AVueAttr;
import com.liukx.spring.client.annotation.AVueRule;

import java.lang.annotation.*;

/**
 * 描述: array数组框
 * <a href="https://avuejs.com/form/form-array.html">查看用法</a>
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueArray {
    /**
     * 展现类型
     */
    String type() default "array";

    /**
     * 属性名称
     */
    String prop() default "";

    /**
     * 文本描述
     */
    String label();

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
     * 字段排序
     */
    int order() default 0;

    /**
     * 是否单独成行
     */
    boolean row() default false;

    /**
     * 是否可以清空选项
     */
    boolean clearable() default false;

    /**
     * 是否禁止编辑
     */
    boolean disabled() default false;

    /**
     * 文字提示
     */
    String tip() default "";

    /**
     * 标题文字提示
     */
    String labelTip() default "";

    /**
     * 辅助语: 比如'请选择','请输入'
     */
    String placeholder() default "";

    /**
     * 深结构数据绑定取值
     * <p>
     * 比如 : deep.deep.deep.value
     */
    String bind() default "";

    /**
     * 是否只读
     * 比如创建时间
     */
    boolean readonly() default false;

    /**
     * 是否可见
     */
    boolean display() default false;
    /* ********************************** 表单常用属性 ***************************************** */

    /**
     * 比例 12 代表一行的一半，24则铺满整行
     */
    int span() default 12;

    /**
     * 表格页面是否展示
     */
    boolean hide() default false;

    /**
     * 表单新增时是否禁止
     */
    boolean addDisabled() default false;

    /**
     * 表单新增时是否可见
     */
    boolean addDisplay() default true;

    /**
     * 表单新增时是否为查看模式
     */
    boolean addDetail() default false;

    /**
     * 表单编辑时是否禁止
     */
    boolean editDisabled() default false;

    /**
     * 表单编辑时是否可见
     */
    boolean editDisplay() default false;

    /**
     * 表单编辑时是否为查看模式
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
     */
    boolean sortable() default false;

    /* ********************************** ********** ******************************************/

    /**
     * 显示边框
     */
    boolean border() default false;

    /**
     * 按钮组类型
     */
    boolean button() default false;

    /**
     * 本地枚举
     */
    String dicData() default "";

    /**
     * 是否作为搜索字段
     */
    boolean search() default false;

    /**
     * 拓展字段
     */
    AVueAttr[] attrExt() default {};

    /**
     * 校验规则
     */
    AVueRule[] rules() default {};

    boolean required() default false;

    /**
     * 查询的单独校验规则
     */
    AVueRule[] searchRequireds() default {};

    /**
     * 查询是否必填
     */
    boolean searchRequired() default false;
}
