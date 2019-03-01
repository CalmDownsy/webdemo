package com.webdemo.system.service.impl;

import com.webdemo.system.dao.UserDao;
import com.webdemo.system.domain.UserDO;
import com.webdemo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/1 17:16
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserDO> list(Map<String, Object> map) {
        return userDao.list(map);
    }
}
