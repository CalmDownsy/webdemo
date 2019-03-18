package com.webdemo.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/15 16:14
 * @Description:
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController {

    @RequiresPermissions("sys:menu:menu")
    @GetMapping
    public String menu() {
        return "/sys/menu/menu";
    }
}
