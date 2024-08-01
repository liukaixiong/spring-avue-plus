package com.liukx.spring.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {

    // 其他API端点
    @GetMapping("/avue/server-crud")
    public String index() {
        return "forward:/avue/index.html";
    }
}
