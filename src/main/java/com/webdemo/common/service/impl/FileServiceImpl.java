package com.webdemo.common.service.impl;

import com.webdemo.common.config.WebDemoConfig;
import com.webdemo.common.dao.FileDao;
import com.webdemo.common.doamin.FileDO;
import com.webdemo.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Autowired
    private WebDemoConfig webDemoConfig;

    @Override
    public Boolean isExist(String url) {
        if (!StringUtils.isEmpty(url)) {
            String filePath = url.replace("/files/", "");
            filePath = webDemoConfig.getUploadPath() + filePath;
            File file = new File(filePath);
            return file.exists();
        }
        return false;
    }

    @Override
    public FileDO get(Long id) {
        return fileDao.get(id);
    }
}
