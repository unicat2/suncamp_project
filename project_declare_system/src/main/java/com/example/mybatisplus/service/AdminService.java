package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {
    Admin myGetById(Long id);
}
