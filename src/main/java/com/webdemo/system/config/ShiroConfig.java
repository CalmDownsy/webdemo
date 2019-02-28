package com.webdemo.system.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zhangsy
 * @Date: 2019/2/27 10:34
 * @Description:
 */
// 配置注解 等同于xml  注册Bean到spring容器
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.cache.type}")
    private String cacheType;

    @Value("${server.session-timeout}")
    private String sessionTimeout;

    // 管理shiro bean的生命周期，初始化和销毁
    // 注册到spring容器
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // thymeleaf 中使用shiro标签
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    // 配置Url进行权限控制
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }

    // shiro框架的核心 管理用户的安全操作
    @Bean
    public SecurityManager securityManager() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();


        return defaultSecurityManager;
    }





}
