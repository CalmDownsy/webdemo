package com.webdemo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: zhangsy
 * @Date: 2019/2/22 14:29
 * @Description:
 */

//取消安全验证 springsecurity 启动时不用验证用户名/密码
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@SpringBootApplication
public class WebDemoApplication {
    public static void main(String[] args) {
//        SpringApplication.run(WebDemoApplication.class, args);
        SpringApplication springApplication = new SpringApplication(WebDemoApplication.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
        System.out.println("\n" +
                "\n" +
                " _____ _             _     _____                             \n" +
                "/  ___| |           | |   /  ___|                            \n" +
                "\\ `--.| |_ __ _ _ __| |_  \\ `--. _   _  ___ ___ ___  ___ ___ \n" +
                " `--. \\ __/ _` | '__| __|  `--. \\ | | |/ __/ __/ _ \\/ __/ __|\n" +
                "/\\__/ / || (_| | |  | |_  /\\__/ / |_| | (_| (_|  __/\\__ \\__ \\\n" +
                "\\____/ \\__\\__,_|_|   \\__| \\____/ \\__,_|\\___\\___\\___||___/___/\n" +
                "                                                             \n" +
                "                                                             \n" +
                "\n");
    }
}
