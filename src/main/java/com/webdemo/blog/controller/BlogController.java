package com.webdemo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: zhangsy
 * @Date: 2019/2/25 09:59
 * @Description:
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @GetMapping()
    public String blog() {
        return "blog/index/main";
    }
}
