package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.AuthorImg;
import com.baomidou.mybatisplus.extension.service.IService;


public interface AuthorImgService extends IService<AuthorImg> {
    void changeAvatar(String phoneNumber, String avatar);
}
