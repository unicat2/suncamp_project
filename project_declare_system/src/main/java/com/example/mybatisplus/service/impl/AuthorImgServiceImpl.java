package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.exception.CustomException;
import com.example.mybatisplus.model.domain.AuthorImg;
import com.example.mybatisplus.mapper.AuthorImgMapper;
import com.example.mybatisplus.service.AdminService;
import com.example.mybatisplus.service.AuthorImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorImgServiceImpl extends ServiceImpl<AuthorImgMapper, AuthorImg> implements AuthorImgService {

    @Autowired(required = false)
    private  AuthorImgMapper authorImgMapper;

    @Override
    public void changeAvatar(String phoneNumber, String avatar) {

        AuthorImg result = authorImgMapper.selectById(phoneNumber);
        if (result == null) {
            throw new CustomException("用户数据不存在");
        }

//        Integer rows = authorImgMapper.updateById(phoneNumber);
//        if (rows != 1) {
//            throw new CustomException("更新用户数据不成功");
//        }
    }

}
