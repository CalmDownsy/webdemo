package com.webdemo.common.dao;

import com.webdemo.common.doamin.DictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/8 14:00
 * @Description:
 */
@Mapper
public interface DictDao {

    List<DictDO> listType();

    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DictDO dictDO);

    int batchRemove(Long[] ids);

    DictDO get(Long id);

    int update(DictDO dictDO);
}
