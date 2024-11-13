# 开发部署介绍

## 启动介绍

### 1. 后端

测试: com.liukx.spring.client.RuoYiClientTestApplication: 启动类

### 2. 前端

1. 启动 `npm run dev`
2. 编译: `npm run build:prod`

**`和标准前端无异`**

### 3. 访问

访问模版列表: http://localhost:5173/avue/list

访问特定的模版: http://localhost:5173/avue/server-crud?group=test-route

## 目录结构

- spring-avue-starter : 作为后端风格
    - com.liukx.spring.client.config.AVueAutoConfiguration: 所有自动配置启动类都在里面
    - META-INF.avue: 该目录下会打包前端项目编译过来的文件目录
    - META-INF.avue.json : 默认的json配置格式，支持直接静态json文件返回
- spring-avue-ui : 前端项目框架
    - spring-avue-ui/src/views : 该类下面是所有相关的页面介绍
    - spring-avue-ui/src/views/crud
        - server-crud.vue : 增删改查页面集中渲染的模版
        - avue-dialog.vue: 弹层的渲染
        - avue-component.vue: 卡片组件的渲染
    - spring-avue-ui/src/api/crud/event
        - rowClickEvent.js : 点击事件触发的通用函数，在后端可配置
        - dialogSubmitEvent.js: 所有弹层的点击提交按钮触发的函数处理,后端可以配置
    - spring-avue-ui/src/mixins
        - server-crud.js : 所有crud的核心函数事件处理器
        - crud-components.js : 新增的组件方法处理类

关于枚举类

DicEnumData

## 注意事项

### 使用相关

1、关于模版和接口相关
模版配置的话，

- 配置相关 @AVueCrudOption 负责crud相关的配置参数
    - enter: 回车触发搜索
    - delBtn : 是否展示删除按钮
    - addBtn : 是否展示新增按钮
- 字段相关
    - 搜索条件字段：如果是纯搜索字段，那么可以配置hide = true，search = true
    - 列表字段：如果你有格式化的需求，比如状态：1 || 0 ， 希望展示有效或者无效 dicData = "AVueDicStatusEnum"
      ,具体参考一下该枚举类,实现DicEnumData即可
    - 时间范围搜索: 请在搜索字段中配置`searchRange=true`即可

### 组件相关

#### 时间组件

1. 如果你希望搜索的时候是范围查询，那么请确保注解中加入了`searchRange=true`
