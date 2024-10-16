package com.liukx.spring.client.annotation.column;

import com.liukx.spring.client.annotation.AVueAttr;
import com.liukx.spring.client.annotation.AVueRule;

import java.lang.annotation.*;

/**
 * 描述: input属性
 * <a href="https://avuejs.com/form/form-select.html">查看用法</a>
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueSelect {
    /**
     * 默认的类型
     */
    String type() default "select";

    /**
     * 属性名称
     */
    String prop() default "";

    /**
     * 文本描述
     */
    String label();

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
     * 开启排序
     */
    boolean sortable() default false;

    /* ********************************** ********** ******************************************/

    /**
     * 级联的默认选项序号
     */
    int cascaderIndex() default -1;

    /**
     * 级联的子节点prop
     */
    String[] cascaderItem() default {};

    /**
     * 是否开启多选
     */
    boolean multiple() default false;

    /**
     * 多选时是否将选中值按文字的形式展示
     */
    boolean tags() default false;

    /**
     * 是否允许用户创建新条目，需配合 filterable 使用
     */
    boolean allowCreate() default false;

    /**
     * 是否可搜索
     */
    boolean filterable() default false;

    /**
     * 是否为远程搜索
     */
    boolean remote() default false;

    /**
     * 在输入框按下回车，选择第一个匹配项。需配合 filterable 或 remote 使用
     */
    boolean defaultFirstOption() default false;

    /**
     * 远程加载时显示的文字
     */
    String loadingText() default "加载中";

    /**
     * 容器内找寻数据
     */
    String dicData() default "";

    /**
     * 加载
     */
    String dicUrl() default "";

    /**
     * 请求方法 [get/post/put/delete]
     */
    String dicMethod() default "";

    /**
     * 格式化值 {'label':'aaa','value':'bbbb'}
     */
    String props() default "";

    /**
     * post的查询参数
     */
    String dicQuery() default "";

    /**
     * 是否作为搜索字段
     */
    boolean search() default false;

    AVueAttr[] attrExt() default {};

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
