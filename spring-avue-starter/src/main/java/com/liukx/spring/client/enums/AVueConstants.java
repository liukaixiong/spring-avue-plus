package com.liukx.spring.client.enums;

/**
 * AVue相关的常量定义
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/15 - 10:42
 */
public class AVueConstants {

    /********************************* 自定义的属性关键字 *********************************/

    /**
     * 新注册组件使用的字段名称key
     */
    public static final String REGISTER_FIELD_KEY = "registerFieldComponents";

    public static final String SEARCH_REQUIRED = "searchRequired";


    public static class Path {
        /**
         * crud的接口路径
         */
        public static final String DEFAULT_URL = "/avue/crud";

        /**
         * 组件的接口路径
         */
        public static final String COMPONENT_URL = "/avue/component";
        /**
         * 客户端的CRUD页面入口访问路径
         */
        public static final String INDEX_CRUD_URL = "/avue/server-crud";

        /**
         * 客户端的通用组件入口访问路径
         */
        public static final String INDEX_COMPONENT_URL = "/avue/avue-component";
    }

}
