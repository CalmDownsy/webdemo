package com.webdemo.system.controller;

import com.webdemo.common.config.WebDemoConfig;
import com.webdemo.common.utils.RandomValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: zhangsy
 * @Date: 2019/2/22 17:20
 * @Description:
 */
@Controller
public class LoginController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private WebDemoConfig webDemoConfig;

    //    主页面
    @GetMapping({"/", ""})
    public String welcome() {
        return "redirect:/blog";
    }

    //  登录页面
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("username", webDemoConfig.getUsername());
        model.addAttribute("password", webDemoConfig.getPassword());
        return "login";
    }

    // 生成验证码
    @GetMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        //响应类型为图片
        response.setContentType("image/jpeg");
        //不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCodeUtil = new RandomValidateCodeUtil();
        randomValidateCodeUtil.getRandcode(request, response);
    }

    @PostMapping("/login")
    @ResponseBody
    public void ajaxLogin() {

    }
}
