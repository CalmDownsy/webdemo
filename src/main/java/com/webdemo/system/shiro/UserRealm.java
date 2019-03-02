package com.webdemo.system.shiro;

import com.webdemo.common.config.ApplicationContextRegister;
import com.webdemo.common.utils.ShiroUtils;
import com.webdemo.system.dao.UserDao;
import com.webdemo.system.domain.UserDO;
import com.webdemo.system.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhangsy
 * @Date: 2019/2/27 16:11
 * @Description:
 */
// 通过Realm 获取用户信息
public class UserRealm extends AuthorizingRealm {

    // 获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = ShiroUtils.getUserId();
        // TODO: 2019/3/2 为什么不能用注解 依赖注入
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        Set<String> userPerms = menuService.listUserPerms(userId);
        //授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(userPerms);
        return info;
    }


    // 获取身份认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // sql查询的参数
        Map<String, Object> map = new HashMap<>(26);
        map.put("username", username);
        // token 中获取密码
        String password = new String((char[])token.getCredentials());
        // TODO: 2019/3/2 为什么不能用注解 依赖注入
        UserDao userDao = ApplicationContextRegister.getBean(UserDao.class);
        UserDO userDO = userDao.list(map).get(0);
        // 校验用户名密码
        if (null == userDO) {
            // 用户名不存在
            throw new UnknownAccountException("账号或密码错误");
        }
        if (!password.equals(userDO.getPassword())) {
            // 密码不正确
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        if (userDO.getStatus() == 0) {
            // 账号锁定
            throw new LockedAccountException("账号被锁定，请联系管理员");
        }
        return new SimpleAuthenticationInfo(userDO, password, getName());
    }
}
