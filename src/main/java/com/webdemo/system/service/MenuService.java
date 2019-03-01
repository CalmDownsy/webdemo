package com.webdemo.system.service;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/1 15:38
 * @Description:
 */
@Service
public interface MenuService {

    Set<String> listUserPerms(Long userId);
}
