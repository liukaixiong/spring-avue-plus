package com.liukx.spring.client.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.*;

/**
 * 诶~ 没办法啊，AVue相关的文档属性太多了,需要一个个去查看,去定义,没办法,想着从官网拷贝表单属性下来,然后放到excel里面解析转换成枚举类
 * <p>
 * 这里暂时做一下记录吧:
 * 关于属性需要关注两个链接:
 * - <a href="https://avuejs.com/crud/crud-doc.html#column-%E5%85%B1%E7%94%A8%E5%B1%9E%E6%80%A7">crud可用的属性</a>
 * - <a href="https://avuejs.com/form/form-doc.html#column-cascader">form表单的属性</a>
 * <p>
 * 第一个涉及到的是一些关于查询、按钮展示（CRUD相关的属性）
 * 第二个是一些关于表单的属性，比如input、select、radio、checkbox、类似的组件属性
 * 二者最好整合，但是太麻烦了，我们还是封装跑出一些常用的吧,细微的可以通过{@link com.liukx.spring.client.annotation.AVueAttr} 去自定义
 *
 * @author liukaixiong
 * @date 2024/8/6 - 16:23
 */
public class AVueDocCase {
    Map<String, String> convertMap = new HashMap<>();
    Set<String> skipStr = new HashSet<>();

    @Test
    public void docTest() {
        String fileName =
                "D:\\tmp\\avue.xlsx";
        ReadAsMapListener readAsMapListener = new ReadAsMapListener();

        EasyExcel.read(fileName).sheet(0).headRowNumber(1).registerReadListener(readAsMapListener).doRead();
        List<Map<String, Object>> dataList = readAsMapListener.getDataList();


        convertMap.put("string", "String");
        convertMap.put("number", "int");
        convertMap.put("-", "\"\"");
        convertMap.put("—", "\"\"");
        convertMap.put("boolean/object", "boolean");
        convertMap.put("boolean/string", "String");
        convertMap.put("left/center/right", "String");
        convertMap.put("object", "String");
        convertMap.put("string/function", "String");


        skipStr.add("function");
        skipStr.add("array");


        for (Map<String, Object> stringObjectMap : dataList) {

            StringBuilder text = new StringBuilder();
            final String type = stringObjectMap.get("类型").toString();
            final String param = stringObjectMap.get("参数").toString();
            final String desc = stringObjectMap.get("说明").toString();
            final String select = stringObjectMap.get("可选值").toString();
            final String defaultValue = stringObjectMap.get("默认值").toString();

            if (!skipStr.add(param) || skipStr.contains(type)) {
                continue;
            }

            text.append("/**").append("\r\n");
            text.append("* ").append(desc).append("\r\n");
            text.append("* ").append("可选值:").append(select).append("\r\n");
            text.append("*/").append("\r\n");
            text.append(convertMap.getOrDefault(type, type)).append(" ").append(param).append("()").append(" default ").append(getDefaultValue(stringObjectMap, convertMap.getOrDefault(defaultValue, defaultValue))).append(";").append("\r\n");

            System.out.println(text);
        }
    }

    private String getDefaultValue(Map<String, Object> objectMap, String defaultValue) {
        final String type = objectMap.get("类型").toString();
        if (type.contains("string") || convertMap.getOrDefault(type, type).equals("String")) {
            return ("\"" + defaultValue + "\"").replaceAll("\"\"\"\"", "\"\"");
        } else {
            return defaultValue;
        }
    }

}
