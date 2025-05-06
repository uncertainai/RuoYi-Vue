package com.ruoyi.common.core.service;


import org.springframework.web.multipart.MultipartFile;

public interface IBaseService<T> {
    void downloadImportModule();

    void importData();
    void importData(MultipartFile file);
}
