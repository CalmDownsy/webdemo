package com.webdemo.common.service.impl;

import com.webdemo.common.dao.DictDao;
import com.webdemo.common.doamin.DictDO;
import com.webdemo.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/8 14:04
 * @Description:
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public List<DictDO> listType() {
        return dictDao.listType();
    }

    @Override
    public List<DictDO> list(Map<String, Object> map) {
        return dictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictDao.count(map);
    }

    @Override
    public int save(DictDO dictDO) {
        return dictDao.save(dictDO);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return dictDao.batchRemove(ids);
    }

    @Override
    public DictDO get(Long id) {
        return dictDao.get(id);
    }

    @Override
    public int update(DictDO dictDO) {
        return dictDao.update(dictDO);
    }
}
