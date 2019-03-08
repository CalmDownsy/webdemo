package com.webdemo.common.service;

import com.webdemo.common.doamin.DictDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/8 14:04
 * @Description:
 */
@Service
public interface DictService {

    List<DictDO> listType();

    List<DictDO> list(Map<String, Object> map);
}
