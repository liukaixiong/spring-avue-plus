//package com.liukx.spring.client.annotation;
//
//import java.lang.annotation.*;
//
///**
// * 描述: crud选项配置
// * <a href="https://avuejs.com/crud/crud-doc.html#option">详细描述</a>
// *
// * @author liukx
// * @date 2021/8/16 18:51
// */
//@Target({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface AVueCrudOption_bak {
//
//
//    /**
//     * 组件的尺寸
//     *
//     * @return
//     */
//    String size() default "small";
//
//    /**
//     * 表格的高度，默认为自动高度。如果设置为auto，会自适应窗口高度，配合calcHeight参数去调节范围
//     *
//     * @return
//     */
//    String height() default "";
//
//    /**
//     * 是否为斑马纹
//     *
//     * @return
//     */
//    boolean stripe() default false;
//
//
//    /**
//     * 表格头部表格
//     *
//     * @return
//     */
//    String title() default "";
//
//
//    /**
//     * 添加按钮 是否显示
//     *
//     * @return
//     */
//    boolean addBtn() default true;
//
//    /**
//     * 表格新增按钮文案
//     *
//     * @return
//     */
//    String addBtnText() default "新增";
//
//    /**
//     * 表格新增按钮图标
//     *
//     * @return
//     */
//    String addBtnIcon() default "";
//
//
//    /**
//     * 新增窗口文案
//     *
//     * @return
//     */
//    String addTitle() default "新增";
//
//    /**
//     * 表格行编辑时添加按钮
//     *
//     * @return
//     */
//    boolean addRowBtn() default false;
//
//    /**
//     * 宽度大小
//     *
//     * @return
//     */
//    String width() default "auto";
//
//    /**
//     * 比例 12 代表一行的一半，24则铺满整行
//     *
//     * @return
//     */
//    int span() default 12;
//
//    /**
//     * 表格列齐方式
//     *
//     * @return
//     */
//    String align() default "left";
//
//
//    /**
//     * 弹窗方式 : drawer 抽屉
//     * dialog/drawer
//     *
//     * @return
//     */
//    String dialogType() default "";
//
//    /**
//     * <el-radio label="ltr">从左往右开</el-radio>
//     * <el-radio label="rtl">从右往左开</el-radio>
//     * <el-radio label="ttb">从上往下开</el-radio>
//     * <el-radio label="btt">从下往上开</el-radio>
//     *
//     * @return
//     */
//    String dialogDirection() default "";
//
//    /**
//     * 回车按键触发提交表单
//     *
//     * @return
//     */
//    boolean enter() default false;
//
//    /**
//     * 表格边框
//     *
//     * @return
//     */
//    boolean border() default false;
//
//    /**
//     * 表格行编辑操作按钮
//     *
//     * @return
//     */
//    boolean cellBtn() default true;
//
//    /**
//     * 表格以卡片分层模式显示，搜索部分和主体部分分层展示
//     *
//     * @return
//     */
//    boolean card() default true;
//
//
//    /**
//     * 行内删除按钮
//     *
//     * @return
//     */
//    boolean delBtn() default true;
//
//    /**
//     * 行内编辑按钮
//     *
//     * @return
//     */
//    boolean viewBtn() default false;
//
//    /**
//     * 行内查看按钮
//     *
//     * @return
//     */
//    String viewBtnText() default "查看";
//
//    /**
//     * 清空按钮文案
//     *
//     * @return
//     */
//    String emptyBtnText() default "清空";
//
//    /**
//     * 表格行复制数据
//     *
//     * @return
//     */
//    boolean copyBtn() default false;
//
//    /**
//     * 是否默认展开所有行，"expand"为true的时候有效
//     *
//     * @return
//     */
//    boolean defaultExpandAll() default false;
//
//    /**
//     * 是否允许拖拽
//     *
//     * @return
//     */
//    boolean dialogDrag() default false;
//
//    /**
//     * 行内编辑按钮
//     *
//     * @return
//     */
//    boolean editBtn() default true;
//
//    /**
//     * 导出Excel按钮
//     *
//     * @return
//     */
//    boolean excelBtn() default false;
//
//    /**
//     * 自定义过滤表格列按钮
//     *
//     * @return
//     */
//    boolean filterBtn() default false;
//
//    /**
//     * 是列的宽度是否自撑开属性
//     *
//     * @return
//     */
//    boolean fit() default true;
//
//    /**
//     * 是否显示序号
//     *
//     * @return
//     */
//    boolean index() default false;
//
//    /**
//     * 序号列宽度
//     *
//     * @return
//     */
//    int indexWidth() default 50;
//
//    /**
//     * 序号列是否冻结列 ，true 表示固定在左侧
//     *
//     * @return
//     */
//    String indexFixed() default "left";
//
//    /**
//     * 序号列的单元格自定义类名
//     *
//     * @return
//     */
//    String indexClassName() default "";
//
//    /**
//     * 序号列标题的自定义类名
//     *
//     * @return
//     */
//    String indexLabelClassName() default "";
//
//    /**
//     * 是否显示操作菜单栏
//     *
//     * @return
//     */
//    boolean menu() default true;
//
//    /**
//     * 操作栏宽度
//     *
//     * @return
//     */
//    int menuWidth() default 220;
//
//    /**
//     * 操作栏列冻结列 ，true 表示固定在左侧
//     * 可选值: true/left/right
//     *
//     * @return
//     */
//    String menuFixed() default "left";
//
//    /**
//     * 操作栏列的单元格自定义类名
//     *
//     * @return
//     */
//    String menuClassName() default "";
//
//    /**
//     * 操作栏列标题的自定义类名
//     *
//     * @return
//     */
//    String menuLabelClassName() default "";
//
//    /**
//     * 操作栏按钮样式
//     * 可选值: 	button/icon/text/menu
//     *
//     * @return
//     */
//    String menuType() default "text";
//
//    /**
//     * 操作栏表头的对齐方式
//     * 可选值: left/center/right
//     *
//     * @return
//     */
//    String menuHeaderAlign() default "center";
//
//    /**
//     * 操作栏按钮的对齐方式
//     * left/center/right
//     *
//     * @return
//     */
//    String menuAlign() default "center";
//
//    /**
//     * 是否显示表头
//     *
//     * @return
//     */
//    boolean showHeader() default true;
//
//    /**
//     * 隐藏表格头部操作
//     *
//     * @return
//     */
//    boolean header() default false;
//
//    /**
//     * 是否显示操作菜单栏
//     *
//     * @return
//     */
//    boolean printBtn() default true;
//
//    /**
//     * 刷新按钮
//     *
//     * @return
//     */
//    boolean refreshBtn() default true;
//
//    /**
//     * 保存按钮
//     *
//     * @return
//     */
//    boolean saveBtn() default true;
//
//    /**
//     * 更新按钮
//     *
//     * @return
//     */
//    boolean updateBtn() default true;
//
//    /**
//     * 取消按钮
//     *
//     * @return
//     */
//    boolean cancelBtn() default true;
//
//    /**
//     * 行的主键
//     *
//     * @return
//     */
//    String rowKey() default "id";
//
//    /**
//     * 编辑窗口文案
//     *
//     * @return
//     */
//    String editTitle() default "编辑";
//
//    /**
//     * 数据为空文案
//     *
//     * @return
//     */
//    String emptyText() default "暂无数据";
//
//    /**
//     * 是否在表尾显示合计行
//     *
//     * @return
//     */
//    boolean showSummary() default false;
//
//    AVueAttr[] attrExt() default {};
//}
//package com.liukx.spring.client.annotation;
//
//import java.lang.annotation.*;
//
///**
// * 描述: crud选项配置
// * <a href="https://avuejs.com/crud/crud-doc.html#option">详细描述</a>
// *
// * @author liukx
// * @date 2021/8/16 18:51
// */
//@Target({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface AVueCrudOption_bak {
//
//
//    /**
//     * 组件的尺寸
//     *
//     * @return
//     */
//    String size() default "small";
//
//    /**
//     * 表格的高度，默认为自动高度。如果设置为auto，会自适应窗口高度，配合calcHeight参数去调节范围
//     *
//     * @return
//     */
//    String height() default "";
//
//    /**
//     * 是否为斑马纹
//     *
//     * @return
//     */
//    boolean stripe() default false;
//
//
//    /**
//     * 表格头部表格
//     *
//     * @return
//     */
//    String title() default "";
//
//
//    /**
//     * 添加按钮 是否显示
//     *
//     * @return
//     */
//    boolean addBtn() default true;
//
//    /**
//     * 表格新增按钮文案
//     *
//     * @return
//     */
//    String addBtnText() default "新增";
//
//    /**
//     * 表格新增按钮图标
//     *
//     * @return
//     */
//    String addBtnIcon() default "";
//
//
//    /**
//     * 新增窗口文案
//     *
//     * @return
//     */
//    String addTitle() default "新增";
//
//    /**
//     * 表格行编辑时添加按钮
//     *
//     * @return
//     */
//    boolean addRowBtn() default false;
//
//    /**
//     * 宽度大小
//     *
//     * @return
//     */
//    String width() default "auto";
//
//    /**
//     * 比例 12 代表一行的一半，24则铺满整行
//     *
//     * @return
//     */
//    int span() default 12;
//
//    /**
//     * 表格列齐方式
//     *
//     * @return
//     */
//    String align() default "left";
//
//
//    /**
//     * 弹窗方式 : drawer 抽屉
//     * dialog/drawer
//     *
//     * @return
//     */
//    String dialogType() default "";
//
//    /**
//     * <el-radio label="ltr">从左往右开</el-radio>
//     * <el-radio label="rtl">从右往左开</el-radio>
//     * <el-radio label="ttb">从上往下开</el-radio>
//     * <el-radio label="btt">从下往上开</el-radio>
//     *
//     * @return
//     */
//    String dialogDirection() default "";
//
//    /**
//     * 回车按键触发提交表单
//     *
//     * @return
//     */
//    boolean enter() default false;
//
//    /**
//     * 表格边框
//     *
//     * @return
//     */
//    boolean border() default false;
//
//    /**
//     * 表格行编辑操作按钮
//     *
//     * @return
//     */
//    boolean cellBtn() default true;
//
//    /**
//     * 表格以卡片分层模式显示，搜索部分和主体部分分层展示
//     *
//     * @return
//     */
//    boolean card() default true;
//
//
//    /**
//     * 行内删除按钮
//     *
//     * @return
//     */
//    boolean delBtn() default true;
//
//    /**
//     * 行内编辑按钮
//     *
//     * @return
//     */
//    boolean viewBtn() default false;
//
//    /**
//     * 行内查看按钮
//     *
//     * @return
//     */
//    String viewBtnText() default "查看";
//
//    /**
//     * 清空按钮文案
//     *
//     * @return
//     */
//    String emptyBtnText() default "清空";
//
//    /**
//     * 表格行复制数据
//     *
//     * @return
//     */
//    boolean copyBtn() default false;
//
//    /**
//     * 是否默认展开所有行，"expand"为true的时候有效
//     *
//     * @return
//     */
//    boolean defaultExpandAll() default false;
//
//    /**
//     * 是否允许拖拽
//     *
//     * @return
//     */
//    boolean dialogDrag() default false;
//
//    /**
//     * 行内编辑按钮
//     *
//     * @return
//     */
//    boolean editBtn() default true;
//
//    /**
//     * 导出Excel按钮
//     *
//     * @return
//     */
//    boolean excelBtn() default false;
//
//    /**
//     * 自定义过滤表格列按钮
//     *
//     * @return
//     */
//    boolean filterBtn() default false;
//
//    /**
//     * 是列的宽度是否自撑开属性
//     *
//     * @return
//     */
//    boolean fit() default true;
//
//    /**
//     * 是否显示序号
//     *
//     * @return
//     */
//    boolean index() default false;
//
//    /**
//     * 序号列宽度
//     *
//     * @return
//     */
//    int indexWidth() default 50;
//
//    /**
//     * 序号列是否冻结列 ，true 表示固定在左侧
//     *
//     * @return
//     */
//    String indexFixed() default "left";
//
//    /**
//     * 序号列的单元格自定义类名
//     *
//     * @return
//     */
//    String indexClassName() default "";
//
//    /**
//     * 序号列标题的自定义类名
//     *
//     * @return
//     */
//    String indexLabelClassName() default "";
//
//    /**
//     * 是否显示操作菜单栏
//     *
//     * @return
//     */
//    boolean menu() default true;
//
//    /**
//     * 操作栏宽度
//     *
//     * @return
//     */
//    int menuWidth() default 220;
//
//    /**
//     * 操作栏列冻结列 ，true 表示固定在左侧
//     * 可选值: true/left/right
//     *
//     * @return
//     */
//    String menuFixed() default "left";
//
//    /**
//     * 操作栏列的单元格自定义类名
//     *
//     * @return
//     */
//    String menuClassName() default "";
//
//    /**
//     * 操作栏列标题的自定义类名
//     *
//     * @return
//     */
//    String menuLabelClassName() default "";
//
//    /**
//     * 操作栏按钮样式
//     * 可选值: 	button/icon/text/menu
//     *
//     * @return
//     */
//    String menuType() default "text";
//
//    /**
//     * 操作栏表头的对齐方式
//     * 可选值: left/center/right
//     *
//     * @return
//     */
//    String menuHeaderAlign() default "center";
//
//    /**
//     * 操作栏按钮的对齐方式
//     * left/center/right
//     *
//     * @return
//     */
//    String menuAlign() default "center";
//
//    /**
//     * 是否显示表头
//     *
//     * @return
//     */
//    boolean showHeader() default true;
//
//    /**
//     * 隐藏表格头部操作
//     *
//     * @return
//     */
//    boolean header() default false;
//
//    /**
//     * 是否显示操作菜单栏
//     *
//     * @return
//     */
//    boolean printBtn() default true;
//
//    /**
//     * 刷新按钮
//     *
//     * @return
//     */
//    boolean refreshBtn() default true;
//
//    /**
//     * 保存按钮
//     *
//     * @return
//     */
//    boolean saveBtn() default true;
//
//    /**
//     * 更新按钮
//     *
//     * @return
//     */
//    boolean updateBtn() default true;
//
//    /**
//     * 取消按钮
//     *
//     * @return
//     */
//    boolean cancelBtn() default true;
//
//    /**
//     * 行的主键
//     *
//     * @return
//     */
//    String rowKey() default "id";
//
//    /**
//     * 编辑窗口文案
//     *
//     * @return
//     */
//    String editTitle() default "编辑";
//
//    /**
//     * 数据为空文案
//     *
//     * @return
//     */
//    String emptyText() default "暂无数据";
//
//    /**
//     * 是否在表尾显示合计行
//     *
//     * @return
//     */
//    boolean showSummary() default false;
//
//    AVueAttr[] attrExt() default {};
//}
