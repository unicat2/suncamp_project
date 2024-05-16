package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.Admin;

public interface AdminMapper extends BaseMapper<Admin> {
    Admin myGetById(Long id);
}
