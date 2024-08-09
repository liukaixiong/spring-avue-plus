package com.liukx.spring.client.handler.impl.json;

import com.liukx.spring.client.annotation.AVueRouteKey;
import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.helper.RouteKeyHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 获取当前项目中的所有AVue规范的链接信息
 *
 * @author liukaixiong
 * @date 2024/8/9 - 14:27
 */
@Component
public class AVueAllUrlListConvert implements AVueJsonDataPropsConvert {

    @Override
    public String convertId() {
        return "aVueAllUrlList";
    }

    @Override
    public void convertValue(String key, Map<String, Object> jsonCache) {
        final List<AVueUrlList> urlList = RouteKeyHelper.getInstance().getAllRouteClass().stream().map(clazz -> {
            AVueRouteKey routeKey = clazz.getAnnotation(AVueRouteKey.class);
            final String groupKey = routeKey.groupKey();
            final String title = routeKey.title();
            final String description = routeKey.description();
            final String img = routeKey.img();
            return new AVueUrlList(title, description, img, AVueConstants.Path.INDEX_CRUD_URL + "?group=" + groupKey);
        }).toList();
        jsonCache.put("data", urlList);
    }

    static class AVueUrlList {
        private String img;
        private String title;
        private String info;
        private String path;

        public AVueUrlList(String title, String info, String img, String path) {
            this.img = img;
            this.title = title;
            this.info = info;
            this.path = path;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
