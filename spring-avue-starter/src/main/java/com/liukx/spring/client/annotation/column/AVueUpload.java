package com.liukx.spring.client.annotation.column;

import com.liukx.spring.client.annotation.AVueAttr;
import com.liukx.spring.client.annotation.AVueRule;
import com.liukx.spring.client.annotation.column.props.AVueUploadPropsHttp;

import java.lang.annotation.*;

/**
 * 描述: 上传组件
 * 参考:{@code @AVueUpload(prop = "image", label = "图片", listType = "picture-img", action = "http://localhost:9403/xxxx/uploadV3", accept = "String", dataType = "String", row = true, propsHttp = @AVueUploadPropsHttp(res = "data", url = "downloadUrl", name = "newFileName"))}
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueUpload {
    /**
     * 展现类型
     */
    String type() default "upload";

    /**
     * upload时文件类型
     * 可选值 : String/Array
     */
    String accept() default "";

    /**
     * 数据类型
     * string : 数据,号分割
     * array : 数组的形式传递给后端
     * 可选值 : string/array
     */
    String dataType() default "";

    /**
     * 必选参数，上传的地址
     */
    String action();

    /**
     * 远程的参数配置
     */
    AVueUploadPropsHttp propsHttp();

    /**
     * 上传文件的大小限制，单位为M,超出回调upload-error函数
     */
    int fileSize() default 0;

    /**
     * 携带的头部附加参数
     */
    AVueAttr[] headers() default {};

    /**
     * 最大允许的上传个数
     */
    int limit() default 100;

    /**
     * 文件类型
     * 可选值 : text/picture/picture-img/picture-card 分别代表，文本、缩略图、单图、图片组
     * 默认值 : text
     */
    String listType() default "text";

    /**
     * 是否显示已上传文件列表
     */
    boolean showFileList() default true;

    /**
     * 是否支持多选文件
     */
    boolean multiple() default false;

    /**
     * 上传的加载文本
     */
    String loadText() default "附件上传中，请稍等";

    /**
     * 是否允许拖拽上传
     */
    boolean drag() default false;

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
