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

    public static final String USER_TOKEN_KEY = "avue-token";


    public static class Path {

        public static final String INDEX_URL = "/avue";
        public static final String INDEX_FULL_URL = INDEX_URL + "/**";

        public static final String LOGIN_HTML_PATH = INDEX_URL + "/login.html";
        /**
         * crud的接口路径
         */
        public static final String DEFAULT_URL = INDEX_URL + "/crud";
        /**
         * crud的登录接口路径
         */
        public static final String DEFAULT_LOGIN_URL = INDEX_URL + "/login";

        /**
         * 组件的接口路径
         */
        public static final String COMPONENT_URL = INDEX_URL + "/component";
        /**
         * 客户端的CRUD页面入口访问路径
         */
        public static final String INDEX_CRUD_URL = INDEX_URL + "/server-crud";
        public static final String COMPONENT_ALL_LIST_URL = INDEX_URL + "/list";
        public static final String INDEX_FORWARD_URL = INDEX_URL + "/index.html";

        /**
         * 客户端的通用组件入口访问路径
         */
        public static final String INDEX_COMPONENT_URL = INDEX_URL + "/avue-component";
    }

}
