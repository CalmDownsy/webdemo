package com.webdemo.system.service;

import com.webdemo.common.doamin.Tree;
import com.webdemo.system.domain.MenuDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/1 15:38
 * @Description:
 */
@Service
public interface MenuService {

    Set<String> listUserPerms(Long userId);

    List<Tree<MenuDO>> listMenuTree(Long id);
}
