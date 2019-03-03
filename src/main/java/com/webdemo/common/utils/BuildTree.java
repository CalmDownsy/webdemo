package com.webdemo.common.utils;


import com.webdemo.common.doamin.Tree;

import java.util.ArrayList;
import java.util.List;

public class BuildTree {

    public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
        if (null == nodes) {
            return null;
        }

        // 首先确定顶级节点
        List<Tree<T>> topNodes = new ArrayList<>();
        // 循环第二次
        // 判断顶节点
        for (Tree<T> children : nodes) {
            String parentId = children.getParentId();
            if (null == parentId || idParam.equals(parentId)) {
                topNodes.add(children);
                continue;
            }
            // 循环第一次
            // 装载子节点
            // 如果当前节点不是顶级节点，再遍历一遍，找出当前节点的父节点
            for (Tree<T> parent : nodes) {
                String id = parent.getId();
                if (null != id && id.equals(parentId)) {
                    parent.getChildren().add(children);
                    parent.setChildren(true);
                    children.setHasParent(true);
                }
            }
        }
        return topNodes;
    }
}
