package com.webdemo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhangsy
 * @Date: 2019/2/25 13:51
 * @Description:
 */
//注册到spring容器中
@Component
@ConfigurationProperties(prefix = "webdemo")
public class WebDemoConfig {
    private String uploadPath;
    private String username;
    private String password;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
