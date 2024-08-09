package com.liukx.spring.client.annotation;

import java.lang.annotation.*;

/**
 * 描述: crud选项配置
 * <a href="https://avuejs.com/crud/crud-doc.html#option">crud详细描述</a>
 * <a href="https://avuejs.com/form/form-doc.html#option">from详细描述</a>
 *
 * @author liukx
 * @date 2021/8/16 18:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AVueCrudOption {


    /**
     * 组件的尺寸
     * 可选值:medium/small/mini
     */
    String size() default "small";

    /**
     * 表格的高度，默认为自动高度。如果设置为auto，会自适应窗口高度，配合calcHeight参数去调节范围
     * 可选值:-
     */
    String height() default "";

    /**
     * 是否为斑马纹
     * 可选值:-
     */
    boolean stripe() default false;

    /**
     * 是否有序号
     * 可选值:-
     */
    boolean index() default false;

    /**
     * 序号列宽度
     * 可选值:-
     */
    int indexWidth() default 50;

    /**
     * 序号列是否冻结列 ，true 表示固定在左侧
     * 可选值:true/left/right
     */
    String indexFixed() default "true";

    /**
     * 序号列的单元格自定义类名
     * 可选值:-
     */
    String indexClassName() default "";

    /**
     * 序号列标题的自定义类名
     * 可选值:-
     */
    String indexLabelClassName() default "";

    /**
     * 是否有操作栏
     * 可选值:-
     */
    boolean menu() default true;

    /**
     * 操作栏宽度
     * 可选值:-
     */
    int menuWidth() default 220;

    /**
     * 操作栏标题
     * 可选值:-
     */
    String menuTitle() default "标题";

    /**
     * 操作栏列冻结列 ，true 表示固定在左侧
     * 可选值:true/left/right
     */
    String menuFixed() default "true";

    /**
     * 操作栏列的单元格自定义类名
     * 可选值:-
     */
    String menuClassName() default "";

    /**
     * 操作栏列标题的自定义类名
     * 可选值:-
     */
    String menuLabelClassName() default "";

    /**
     * 操作栏按钮样式
     * 可选值:button/icon/text/menu
     */
    String menuType() default "text";

    /**
     * 操作栏表头的对齐方式
     * 可选值:left/center/right
     */
    String menuHeaderAlign() default "center";

    /**
     * 操作栏按钮的对齐方式
     * 可选值:left/center/right
     */
    String menuAlign() default "center";

    /**
     * 是否有选择框
     * 可选值:-
     */
    boolean selection() default false;

    /**
     * 选择框列宽度
     * 可选值:-
     */
    int selectionWidth() default 50;

    /**
     * 选择框列是否冻结列 ，true 表示固定在左侧
     * 可选值:true/left/right
     */
    String selectionFixed() default "true";

    /**
     * 选择框列的单元格自定义类名
     * 可选值:-
     */
    String selectionClassName() default "";

    /**
     * 选择框列标题的自定义类名
     * 可选值:-
     */
    String selectionLabelClassName() default "";

    /**
     * 是否有展开表格
     * 可选值:-
     */
    boolean expand() default false;

    /**
     * 展开列宽度
     * 可选值:-
     */
    int expandWidth() default 50;

    /**
     * 展开列是否冻结列 ，true 表示固定在左侧
     * 可选值:true/left/right
     */
    String expandFixed() default "true";

    /**
     * 展开列的单元格自定义类名
     * 可选值:-
     */
    String expandClassName() default "";

    /**
     * 展开列标题的自定义类名
     * 可选值:-
     */
    String expandLabelClassName() default "";

    /**
     * 是否带有纵向边框
     * 可选值:-
     */
    boolean border() default false;

    /**
     * 在数据更新之后保留之前选中的数据（需指定 rowKey）
     * 可选值:-
     */
    boolean reserveSelection() default false;

    /**
     * 列的宽度是否自撑开
     * 可选值:-
     */
    boolean fit() default true;

    /**
     * 是否显示表头
     * 可选值:-
     */
    boolean showHeader() default true;

    /**
     * 隐藏表格头部操作
     * 可选值:-
     */
    boolean header() default false;

    /**
     * 是否要高亮当前行
     * 可选值:-
     */
    boolean highlightCurrentRow() default false;

    /**
     * 行数据的 Key，用来优化 Table 的渲染；在使用 reserve-selection 功能与显示树形数据时，该属性是必填的。
     * 可选值:-
     */
    String rowKey() default "id";

    /**
     * 行数据的 Key，的父类Key用于局部刷新树形表格。
     * 可选值:-
     */
    String rowParentKey() default "parentId";

    /**
     * 空数据时显示的文本内容
     * 可选值:-
     */
    String emptyText() default "暂无数据";

    /**
     * 是否默认展开所有行，当前表格包含展开行存在或者为树形表格时有效
     * 可选值:-
     */
    boolean defaultExpandAll() default false;

    /**
     * 默认的排序列的 prop 和顺序。它的prop属性指定默认的排序的列，order指定默认排序的顺序
     * 可选值:ascending/ descending
     */
    String defaultSort() default "ascending";

    /**
     * 是否在表尾显示合计行
     * 可选值:-
     */
    boolean showSummary() default false;

    /**
     * 全局当内容过长被隐藏时显示 tooltip详请参考tooltip-options
     * 可选值:-
     */
    boolean showOverflowTooltip() default false;

    /**
     * 溢出的 tooltip 的 effect
     * 可选值:dark/light
     */
    String tooltipEffect() default "dark";

    /**
     * 溢出 tooltip 的选项，参见 tooltip 组件
     * 可选值:-
     */
    String tooltipOptions() default "left";

    /**
     * 是否懒加载子节点数据,会调用tree-load方法回调
     * 可选值:-
     */
    boolean lazy() default false;

    /**
     * 表格标题
     * 可选值:-
     */
    String title() default "";

    /**
     * 表格新增按钮
     * 可选值:-
     */
    boolean addBtn() default true;

    /**
     * 表格新增按钮文案
     * 可选值:-
     */
    String addBtnText() default "新增";

    /**
     * 表格新增按钮图标
     * 可选值:-
     */
    String addBtnIcon() default "";

    /**
     * 表格修改按钮
     * 可选值:-
     */
    boolean editBtn() default true;

    /**
     * 表格修改按钮文案
     * 可选值:-
     */
    String editBtnText() default "修改";

    /**
     * 表格修改按钮图标
     * 可选值:-
     */
    String editBtnIcon() default "";

    /**
     * 表格删除按钮
     * 可选值:-
     */
    boolean delBtn() default true;

    /**
     * 表格删除按钮文案
     * 可选值:-
     */
    String delBtnText() default "删除";

    /**
     * 表格删除按钮图标
     * 可选值:-
     */
    String delBtnIcon() default "";

    /**
     * 表格查看按钮
     * 可选值:-
     */
    boolean viewBtn() default false;

    /**
     * 表格查看按钮文案
     * 可选值:-
     */
    String viewBtnText() default "查看";

    /**
     * 表格查看按钮图标
     * 可选值:-
     */
    String viewBtnIcon() default "";

    /**
     * 表格弹窗修改按钮
     * 可选值:-
     */
    boolean updateBtn() default true;

    /**
     * 表格弹窗修改按钮文案
     * 可选值:-
     */
    String updateBtnText() default "修改";

    /**
     * 表格弹窗修改按钮图标
     * 可选值:-
     */
    String updateBtnIcon() default "";

    /**
     * 表格弹窗保存按钮
     * 可选值:-
     */
    boolean saveBtn() default true;

    /**
     * 表格弹窗保存按钮文案
     * 可选值:-
     */
    String saveBtnText() default "保存";

    /**
     * 表格弹窗保存按钮图标
     * 可选值:-
     */
    String saveBtnIcon() default "";

    /**
     * 表格弹窗取消按钮
     * 可选值:-
     */
    boolean cancelBtn() default true;

    /**
     * 表格弹窗取消按钮文案
     * 可选值:-
     */
    String cancelBtnText() default "取消";

    /**
     * 表格弹窗取消按钮图标
     * 可选值:-
     */
    String cancelBtnIcon() default "";

    /**
     * 表格搜索按钮
     * 可选值:-
     */
    boolean searchBtn() default true;

    /**
     * 表格搜索按钮文案
     * 可选值:-
     */
    String searchBtnText() default "搜索";

    /**
     * 表格搜索按钮图标
     * 可选值:-
     */
    String searchBtnIcon() default "";

    /**
     * 表格清空按钮
     * 可选值:-
     */
    boolean emptyBtn() default true;

    /**
     * 表格清空按钮文案
     * 可选值:-
     */
    String emptyBtnText() default "清空";

    /**
     * 表格清空按钮图标
     * 可选值:-
     */
    String emptyBtnIcon() default "";

    /**
     * 表格打印按钮
     * 可选值:-
     */
    boolean printBtn() default false;

    /**
     * 表格导出按钮
     * 可选值:-
     */
    boolean excelBtn() default false;

    /**
     * 表格自定义过滤按钮
     * 可选值:-
     */
    boolean filterBtn() default false;

    /**
     * 表格刷新数据按钮
     * 可选值:-
     */
    boolean refreshBtn() default true;

    /**
     * 表格列操作按钮
     * 可选值:-
     */
    boolean columnBtn() default true;

    /**
     * 表格搜索显隐按钮
     * 可选值:-
     */
    boolean searchShowBtn() default true;

    /**
     * 表格复制数据按钮
     * 可选值:-
     */
    boolean copyBtn() default false;

    /**
     * 卡片模式切换按钮
     * 可选值:-
     */
    boolean gridBtn() default true;

    /**
     * 卡片模式格栅
     * 可选值:-
     */
    int gridSpan() default 8;

    /**
     * 卡片模式背景图片
     * 可选值:-
     */
    String gridBackgroundImage() default "";

    /**
     * 卡片模式背景颜色
     * 可选值:-
     */
    String gridBackground() default "";

    /**
     * 表格新增弹窗标题
     * 可选值:-
     */
    String addTitle() default "新增";

    /**
     * 表格修改弹窗标题
     * 可选值:-
     */
    String editTitle() default "修改";

    /**
     * 表格查看弹窗标题
     * 可选值:-
     */
    String viewTitle() default "查看";

    /**
     * 表格弹窗是否可以拖拽
     * 可选值:-
     */
    boolean dialogDrag() default false;

    /**
     * 表格弹窗是否为全屏
     * 可选值:-
     */
    boolean dialogFullscreen() default false;

    /**
     * 表格弹窗自定义class
     * 可选值:-
     */
    String dialogCustomClass() default "";

    /**
     * 表格弹窗是否可以通过按下ESC关闭
     * 可选值:-
     */
    boolean dialogEscape() default true;

    /**
     * 表格弹窗是否可以通过点击modal关闭
     * 可选值:-
     */
    boolean dialogClickModal() default false;

    /**
     * 表格弹窗是否显示关闭按钮
     * 可选值:-
     */
    boolean dialogCloseBtn() default true;

    /**
     * 表格弹窗是否需要遮罩层
     * 可选值:-
     */
    boolean dialogModal() default true;

    /**
     * 表格弹窗框按钮的位置
     * 可选值:left/center/right
     */
    String dialogMenuPosition() default "right";

    /**
     * 表格弹窗顶部的距离
     * 可选值:-
     */
    int dialogTop() default 25;

    /**
     * 表格弹窗方式
     * 可选值:dialog/drawer
     */
    String dialogType() default "dialog";

    /**
     * 表格弹窗打开方向
     * 可选值:rtl/ltr/ttb/tbb
     */
    String dialogDirection() default "rtl";

    /**
     * 表格弹窗宽度
     * 可选值:-
     */
    String dialogWidth() default "60%";

    /**
     * 表格搜索首次是否展示
     * 可选值:-
     */
    boolean searchShow() default true;

    /**
     * 表格搜索半收缩按钮
     * 可选值:-
     */
    boolean searchIcon() default false;

    /**
     * 表格搜索半收缩个数
     * 可选值:-
     */
    int searchIndex() default 2;

    /**
     * 表格搜索按钮位置
     * 可选值:-
     */
    String searchMenuPosition() default "center";

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
     * 搜索项标题宽度
     * 可选值:—
     */
    int searchLabelWidth() default 80;

    /**
     * 搜索项标题位置
     * 可选值:left/right/top
     */
    String searchLabelPosition() default "left";

    /**
     * 标题宽度
     * 可选值:-
     */
    String labelWidth() default "80";

    /**
     * 标题位置
     * 可选值:left/right/top
     */
    String labelPosition() default "left";

    /**
     * 标题的后缀
     * 可选值:-
     */
    String labelSuffix() default ":";

    /**
     * 回车按键触发提交表单
     * 可选值:-
     */
    boolean enter() default false;

    /**
     * 选项卡表单(前提要配置group)
     * 可选值:-
     */
    boolean tabs() default false;

    /**
     * 表单操作栏是否显示
     * 可选值:-
     */
    boolean menuBtn() default true;

    /**
     * 表单操作菜单栅格占据的列数
     * 可选值:-
     */
    int menuSpan() default 24;

    /**
     * 表单操作菜单的位置
     * 可选值:left/center/right
     */
    String menuPosition() default "center";

    /**
     * 提交按钮
     * 可选值:-
     */
    boolean submitBtn() default true;

    /**
     * 提交按钮文案
     * 可选值:-
     */
    String submitText() default "提交";

    AVueAttr[] attrExt() default {};
}
