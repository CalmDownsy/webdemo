package com.webdemo.system.dao;

import com.webdemo.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/1 16:57
 * @Description:
 */
@Mapper
public interface UserDao {

    List<UserDO> list(Map<String, Object> map);
}
