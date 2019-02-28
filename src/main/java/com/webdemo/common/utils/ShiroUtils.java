package com.webdemo.common.utils;

import com.webdemo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Auther: zhangsy
 * @Date: 2019/2/27 16:39
 * @Description:
 */
public class ShiroUtils {

    // 当前的操作用户
    private static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    // 获取用户信息 Principal带有用户名的实体对象
    public static UserDO getUser() {
        Object o = getSubject().getPrincipal();
        return (UserDO) o;
    }

    public static Long getUserId() {
        return getUser().getUserId();
    }

    public static void logout() {
        getSubject().logout();
    }
}
