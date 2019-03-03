package com.webdemo.system.service.impl;

import com.webdemo.common.doamin.Tree;
import com.webdemo.common.utils.BuildTree;
import com.webdemo.system.dao.MenuDao;
import com.webdemo.system.domain.MenuDO;
import com.webdemo.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/1 15:41
 * @Description:
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public Set<String> listUserPerms(Long userId) {
        List<String> perms = menuDao.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<MenuDO>> listMenuTree(Long id) {
        List<Tree<MenuDO>> trees = new ArrayList<>();
        List<MenuDO> menuDOList = menuDao.listMenuByUserId(id);
        for (MenuDO menuDO : menuDOList) {
            Tree<MenuDO> tree = new Tree<>();
            tree.setId(menuDO.getMenuId().toString());
            tree.setParentId(menuDO.getParentId().toString());
            tree.setText(menuDO.getName());
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("url", menuDO.getUrl());
            attributes.put("icon", menuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        return BuildTree.buildList(trees, "0");
    }
}
