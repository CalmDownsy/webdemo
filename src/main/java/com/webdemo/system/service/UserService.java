package com.webdemo.system.service;

import com.webdemo.system.domain.UserDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/1 17:14
 * @Description:
 */
@Service
public interface UserService {

    List<UserDO> list(Map<String, Object> map);
}
