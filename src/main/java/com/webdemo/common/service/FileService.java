package com.webdemo.common.service;

import com.webdemo.common.doamin.FileDO;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    FileDO get(Long id);

    Boolean isExist(String url);
}
