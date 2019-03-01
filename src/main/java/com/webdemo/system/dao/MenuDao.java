package com.webdemo.system.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/1 15:29
 * @Description:
 */
@Mapper
public interface MenuDao {

    // 查询用户权限
    List<String> listUserPerms(Long id);
}
