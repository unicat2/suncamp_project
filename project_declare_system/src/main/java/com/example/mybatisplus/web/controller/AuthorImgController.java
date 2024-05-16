package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.AuthorImgService;
import com.example.mybatisplus.model.domain.AuthorImg;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/authorImg")
public class AuthorImgController {
    private final Logger logger = LoggerFactory.getLogger( AuthorImgController.class );

    @Autowired
    private AuthorImgService authorImgService;


    /*上传或更改头像*/
    @RequestMapping("/upload")
    @ResponseBody
    public JsonResponse<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("phoneNumber") String phoneNumber) {

        AuthorImg authorImg = new AuthorImg().setPhoneNumber(phoneNumber);

        authorImg.setImgPath(FileUtil.upload(file,"authorImg/"));
        //头像存在则更新
        authorImgService.saveOrUpdate(authorImg);
        return JsonResponse.success("success");
    }

    @RequestMapping("/get")
    @ResponseBody
    public JsonResponse<AuthorImg> get(@RequestParam("phoneNumber") String phoneNumber) {

        AuthorImg authorImg = authorImgService.getById(phoneNumber);
        //头像存在则返回
        return JsonResponse.success(authorImg);
    }




}

