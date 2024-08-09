package com.liukx.spring.client.excel;

public @interface ColumnCase {
    /**
     * 列标题
     * 可选值:—
     */
    String label() default "";

    /**
     * 列标题的内容属性名称
     * 可选值:—
     */
    String prop() default "";

    /**
     * 对应列的宽度
     * 可选值:—
     */
    String width() default "";

    /**
     * 深结构数据绑定值
     * 可选值:—
     */
    String bind() default "";

    /**
     * 对应列的最小宽度，与 width 的区别是 width 是固定的，minWidth 会把剩余宽度按比例分配给设置了 minWidth 的列
     * 可选值:—
     */
    String minWidth() default "";

    /**
     * 列是否固定在左侧或者右侧，true 表示固定在左侧
     * 可选值:true/left/right
     */
    String fixed() default "";

    /**
     * 列的单元格自定义类名
     * 可选值:-
     */
    String className() default "";

    /**
     * 列标题的自定义类名
     * 可选值:-
     */
    String labelClassName() default "";

    /**
     * 对应列是否可以排序，如果设置为 'custom'，则代表用户希望远程排序，需要监听 Table 的 sort-change 事件
     * 可选值:true, false, 'custom'
     */
    String sortable() default "";

    /**
     * 当内容过长被隐藏时显示 tooltip详请参考tooltip-options
     * 可选值:-
     */
    boolean overHidden() default false;

    /**
     * 当内容过长被隐藏时显示 tooltip详请参考tooltip-options
     * 可选值:-
     */
    boolean showOverflowTooltip() default false;

    /**
     * 对齐方式
     * 可选值:left/center/right
     */
    String align() default "left";

    /**
     * 表头对齐方式，若不设置该项，则使用表格的对齐方式
     * 可选值:left/center/right
     */
    String headerAlign() default "left";

    /**
     * 数据过滤的选项。
     * 可选值:-
     */
    boolean filters() default false;

    /**
     * 卡片模式栅格后面的内容是否从新的一行开始展示
     * 可选值:-
     */
    boolean gridRow() default false;

    /**
     * 是否为搜索项
     * 可选值:—
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
     * 弹出表单是否显示
     * 可选值:-
     */
    boolean display() default true;

    /**
     * 弹出表单是否禁止
     * 可选值:-
     */
    boolean disabled() default false;

    /**
     * 表单新增时项是否禁止
     * 可选值:—
     */
    boolean addDisabled() default false;

    /**
     * 表单编辑时项是否禁止
     * 可选值:—
     */
    boolean editDisabled() default false;

    /**
     * 表单查看时项是否禁止
     * 可选值:—
     */
    boolean viewDisabled() default false;

    /**
     * 表单新增时项是否显示
     * 可选值:—
     */
    boolean addDisplay() default true;

    /**
     * 表单编辑时项是否显示
     * 可选值:—
     */
    boolean editDisplay() default true;

    /**
     * 表单查看时项是否显示
     * 可选值:—
     */
    boolean viewDisplay() default true;

    /**
     * 对应列是否可以通过拖动改变宽度（需要在option中border属性为真）
     * 可选值:—
     */
    boolean resizable() default true;

    /**
     * 选项的值为选项对象的某个属性值
     * 可选值:—
     */
    String value() default "value";

    /**
     * 选项的子选项为选项对象的某个属性值
     * 可选值:—
     */
    String children() default "children";

    /**
     * 选项返回结构的层级(例如data.data)
     * 可选值:—
     */
    String res() default "";
}
