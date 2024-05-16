package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Author;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthorService extends IService<Author> {

    IPage<Author> myQuery(Page page);
    IPage<Author> mySecQuery(Page page, String writerPhoneNumber);



}
