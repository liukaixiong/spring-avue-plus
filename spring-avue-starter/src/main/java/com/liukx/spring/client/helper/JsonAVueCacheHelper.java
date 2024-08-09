package com.liukx.spring.client.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * json缓存设置
 *
 * @author liukaixiong
 * @date 2024/7/31 - 15:50
 */
public class JsonAVueCacheHelper {
    private final static Logger logger = LoggerFactory.getLogger(JsonAVueCacheHelper.class);
    private final Map<String, Map<String, Object>> jsonObjectMap = new HashMap<>();
    private final Gson gson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();
    private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    private static final JsonAVueCacheHelper INSTANCE = new JsonAVueCacheHelper();

    public static JsonAVueCacheHelper getINSTANCE() {
        return INSTANCE;
    }

    private String[] jsonPaths;

    public void registerJsonPaths(String[] jsonPaths) {
        this.jsonPaths = jsonPaths;

        String defaultJsonPath = "classpath:/META-INF/avue/json/**";

        if (getJsonPaths().length == 0) {
            this.jsonPaths = new String[]{defaultJsonPath};
        }

        for (String jsonPath : jsonPaths) {
            builderResourceCache(jsonPath);
        }
    }

    public String[] getJsonPaths() {
        return jsonPaths;
    }

    private void builderResourceCache(String jsonPath) {
        String fileName = "";
        try {
            Resource[] resources = resolver.getResources(jsonPath);
            for (Resource resource : resources) {
                if (resource.exists()) {
                    fileName = resource.getFilename();
                    if (Objects.requireNonNull(fileName).endsWith(".js")) {
                        final String javascriptValue = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
                        String json = javascriptValue.substring(javascriptValue.indexOf("{"), javascriptValue.lastIndexOf("}") + 1);
                        final Map<String, Object> jsonObject = gson.fromJson(json, Map.class);

                        Object routeKey = jsonObject.get("routeKey");

                        if (routeKey == null) {
                            routeKey = fileName.split("\\.")[0];
                        }
                        jsonObjectMap.put(routeKey.toString(), jsonObject);
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("读取文件异常 -> {}->{}", fileName, e.getMessage());
        }
    }

    public Map<String, Object> getJsonObject(String routeKey) {
        return this.jsonObjectMap.get(routeKey);
    }

    public void refreshCache() {
        for (String jsonPath : getJsonPaths()) {
            builderResourceCache(jsonPath);
        }
    }
}
