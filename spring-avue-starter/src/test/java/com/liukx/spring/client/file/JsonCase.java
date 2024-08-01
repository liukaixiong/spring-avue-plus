package com.liukx.spring.client.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author liukaixiong
 * @date 2024/7/30 - 19:11
 */
public class JsonCase {


    public static void main(String[] args) throws Exception {
        String jsFilePath = "D:\\project\\zhiyueshufang\\spring-avue-plus\\spring-avue-starter\\src\\test\\resources\\json";
        Gson gson = new GsonBuilder().setStrictness(Strictness.LENIENT).create();

        Files.list(Paths.get(jsFilePath)).forEach(path -> {
            try {
                String content = Files.readString(path);
                String json = content.substring(content.indexOf("{"), content.lastIndexOf("}") + 1);
                final Map map = gson.fromJson(json, Map.class);
                System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
