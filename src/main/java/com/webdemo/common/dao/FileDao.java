package com.webdemo.common.dao;


import com.webdemo.common.doamin.FileDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao {

    FileDO get(Long id);
}
