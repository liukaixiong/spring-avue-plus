## 流程及拓展点介绍

流程介绍:

> 其实本质上都是解析注解成json，所以先处理注解，然后围绕属性重新做一些后置处理。
>
> 你可以根据自己的需求实现对应的接口，完成某些实现。

## 1、AVueHandler: 注解处理器

注解处理器，负责处理所有模版的注解

#### 1.1 DefaultAnnotationHandler: 过滤默认值

默认值处理，保留重写后的值，默认值会被过滤掉

#### 1.2 SupportNestingAnnotationHandler: 支持嵌套处理

目前用来处理`@AVueDynamic` 、`@AVueTable` 相关注解

#### 1.3 GroupAnnotationHandler : @AVueGroup注解单独处理

分组处理，一些新的属性处理可以参考

#### 1.4 NewImportComponentsHandler : 新集成的组件实现参考

如果你有一些比较喜欢的组件集成到框架中，可以参考这个全新的注解处理，自定义配置属性。

## 2、AVueAttrPostProcess: 属性后置处理器

#### 2.1 AttrColumnProcess: @AVueAttr属性处理

#### 2.2 DefaultFieldValueProcess: 模版中的默认值处理

#### 2.3 DicDataProcess: 本地字典处理,也就是枚举

#### 2.4 JsonDataProcess : props、dicQuery 属性转换成json处理

#### 2.5 RuleDataProcess: rule校验规则属性字段处理

#### 2.6 SpringValueProcess : 特定属性的上下文变量转换

## 3、IAVueLoginService: 帐号验证

负责用户名和密码的校验，你可以做一些自己的登录体系。

## 4、IAVueTokenService: token构建

负责用户的token生成和校验

## 5. 枚举相关

### 通用枚举:

- AVueDicBooleanEnum : 是否枚举
- AVueDicStatusEnum : 有效无效枚举

### 5.1 DicEnumData 普通枚举

可以定义枚举之后实现该接口，然后会根据你的接口的code和label来展示对应的编码和描述

```java
@AVueSelect(prop = "checkStatus", label = "认证状态", dicData = "CheckStatusEnums", search = true)
private String checkStatus = CheckStatusEnums.FOOTBALL.getCode().toString();
```

```java
public enum CheckStatusEnums implements DicEnumData {
    BASKETBALL("1", "已认证"),
    FOOTBALL("0", "未认证"),
    BADMINTON("-99", "已失效"),
    BILLIARDS("-1", "已拒绝");

    private String code;
    private String label;

    CheckStatusEnums(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Object getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

}
```

### 5.2 DictionaryDataCallback 回调枚举

可以通过查询数据库、缓存或者第三方等等来实现,会通过容器找到对应的实现并填充

**使用方式**

```java
@AVueSelect(prop = "likeStar", label = "喜欢明星", dicData = "test-likeStar-map", dicUrl = "/liukx")
private Integer likeStar;
```

```java
@Component
public class TestModelProcessor implements DictionaryDataCallback<Map<String, Object>> {

    @Override
    public String callName() {
        return "test-likeStar-map";
    }

    @Override
    public PropsModel props() {
        //
        return new PropsModel("username", "id");
    }

    @Override
    public List<Map<String, Object>> call(AVueAttrLevel level, AnnotatedElement element, Map<String, Object> attrMap) {
        // 模拟数据
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(mock(1, "周杰伦"));
        list.add(mock(2, "林俊杰"));
        list.add(mock(3, "王力宏"));
        list.add(mock(4, "许嵩"));
        return list;
    }

    private Map<String, Object> mock(int id, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", name);
        return map;
    }
}
```

