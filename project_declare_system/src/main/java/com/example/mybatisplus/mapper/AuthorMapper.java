package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface AuthorMapper extends BaseMapper<Author> {

    IPage<Author> myQuery(Page page);

    IPage<Author> mySecQuery(Page page, String writerPhoneNumber);

}
