package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.LoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LoginUserService extends IService<LoginUser> {
    LoginUser getByPhone(String phoneNumber);
}
