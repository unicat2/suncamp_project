package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Author;
import com.example.mybatisplus.mapper.AuthorMapper;
import com.example.mybatisplus.service.AuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {


    @Autowired
    AuthorMapper authorMapper;

    @Override
    public IPage<Author> myQuery(Page page) {
        return authorMapper.myQuery(page);
    }

    @Override
    public IPage<Author> mySecQuery(Page page, String writerPhoneNumber) {
        return authorMapper.mySecQuery(page,writerPhoneNumber);
    }
}
