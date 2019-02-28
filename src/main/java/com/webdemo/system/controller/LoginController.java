package com.webdemo.system.controller;

import com.webdemo.common.config.WebDemoConfig;
import com.webdemo.common.utils.MD5Utils;
import com.webdemo.common.utils.RandomValidateCodeUtil;
import com.webdemo.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    public Result ajaxLogin(String username, String password, String verify, HttpServletRequest request) {
        // 从session中取出验证码
        String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
        // 校验
        if (StringUtils.isBlank(verify)) {
            return Result.error("请输入验证码");
        }
        if (!random.equals(verify)) {
            return Result.error("请输入正确的验证码");
        }
        // TODO: 2019/2/26 shiro 看不懂，先不做，直接跳到index主页面
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return Result.ok();
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index_v1";
    }
}
