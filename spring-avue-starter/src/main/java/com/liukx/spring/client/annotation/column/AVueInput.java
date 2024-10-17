package com.liukx.spring.client.annotation.column;

import com.liukx.spring.client.annotation.AVueAttr;
import com.liukx.spring.client.annotation.AVueRule;

import java.lang.annotation.*;

/**
 * 描述: input属性
 * <a href="https://avuejs.com/form/form-input.html">查看文档</a>
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueInput {
    /**
     * 展现类型
     */
    String type() default "input";

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
     * 列的单元格自定义类名
     */
    String className() default "";

    /**
     * 列标题的自定义类名
     */
    String labelClassName() default "";

    /**
     * 对齐方式
     */
    String align() default "left";

    /**
     * 表头对齐方式，若不设置该项，则使用表格的对齐方式
     */
    String headerAlign() default "left";


    /**
     * 数据过滤的选项。
     */
    boolean filters() default false;

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
     * 弹出表单是否禁止
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
     * 弹出表单是否显示
     */
    boolean display() default false;

    /**
     * 表格页面是否展示
     */
    boolean hide() default false;

    /* ********************************** 表单常用属性 ***************************************** */

    /**
     * 比例 12 代表一行的一半，24则铺满整行
     */
    int span() default 12;

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
    boolean editDisplay() default true;

    /**
     * 表单编辑时是否为查看模式
     */
    boolean editDetail() default false;

    /**
     * 开启排序
     */
    boolean sortable() default false;

    /* ********************************** ********** ******************************************/

    /********************************** 事件属性 ***********************************/
    String onClick() default "";

    String onBlur() default "";

    String onFocus() default "";

    String onChange() default "";

    /**
     * 最小行/最小值
     */
    int minRows() default 2;

    /**
     * 最大行/最大值
     */
    int maxRows() default 4;

    /**
     * 密码是否可见
     */
    boolean showPassword() default true;

    /**
     * 是否显示输入字数统计
     */
    boolean showWordLimit() default true;

    /**
     * 输入框头部图标
     */
    String prefixIcon() default "";

    /**
     * 输入框尾部图标
     */
    String suffixIcon() default "";

    /**
     * 是否作为搜索字段
     */
    boolean search() default false;

    /**
     * 搜索项初始化值
     * 可选值:—
     */
    String searchValue() default "";

    /**
     * 搜索项辅助文字
     * 可选值:—
     */
    String searchPlaceholder() default "";

    /**
     * 搜索项框栅列
     * 可选值:—
     */
    int searchSpan() default 8;

    /**
     * 搜索项位置排序，序号越大越靠前
     * 可选值:—
     */
    int searchOrder() default 100;

//    /**
//     * 搜索项框间距
//     * 可选值:—
//     */
//    int searchGutter() default "";

    /**
     * 搜索项为范围搜索
     * 可选值:—
     */
    boolean searchRange() default false;

    /**
     * 搜索项的类型
     * 可选值:—
     */
    String searchType() default "";

    /**
     * 搜索项标题宽度
     * 可选值:—
     */
    int searchLabelWidth() default 80;

    /**
     * 搜索项清除
     * 可选值:—
     */
    boolean searchClearable() default false;

    /**
     * 搜索项是否多选
     * 可选值:—
     */
    boolean searchMultiple() default false;

    /**
     * 搜索项将选中值按文字的形式展示
     * 可选值:—
     */
    boolean searchTags() default false;

    /**
     * 搜索项标题位置
     * 可选值:left/right/top
     */
    String searchLabelPosition() default "left";

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
