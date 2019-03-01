package com.webdemo.system.shiro;

import com.webdemo.common.config.ApplicationContextRegister;
import com.webdemo.common.utils.ShiroUtils;
import com.webdemo.system.service.MenuService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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

        return null;
    }
}
