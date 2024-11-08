package com.liukx.spring.client.helper;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/19 - 16:30
 */
@SuppressWarnings("rawtypes")
public class DicEnumsHelper {

    private static final DicEnumsHelper INSTANCE = new DicEnumsHelper();

    private final Map<String, Class<? extends Enum>> enumCache = new ConcurrentHashMap<>();

    public static DicEnumsHelper getINSTANCE() {
        return INSTANCE;
    }

    public void register(Class<? extends Enum> clazz) {
        enumCache.put(clazz.getSimpleName(), clazz);
    }

    public Class<? extends Enum> get(String simpleName) {
        return enumCache.get(simpleName);
    }

    public Set<String> getKeys() {
        return enumCache.keySet();
    }

    public List<Class<? extends Enum>> getValues() {
        return enumCache.values().stream().collect(Collectors.toList());
    }
}
