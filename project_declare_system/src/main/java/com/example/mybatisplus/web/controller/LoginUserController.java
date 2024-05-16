package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.mybatisplus.common.utls.RandomValidateCode;
import com.example.mybatisplus.model.dto.Modify;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.LoginUserService;
import com.example.mybatisplus.model.domain.LoginUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
@Slf4j
public class LoginUserController {
    private final Logger logger = LoggerFactory.getLogger( LoginUserController.class );

    @Autowired
    private LoginUserService loginUserService;

    @PostMapping(value = "/check")
    @ApiOperation(value = "用户登录接口")
    @ResponseBody
    public JsonResponse check(@RequestBody LoginUser loginUser) {

        log.info("用户手机号：" + loginUser.getPhoneNumber());
        log.info("登陆密码" + loginUser.getPassword());

        LoginUser getUser = loginUserService.getByPhone(loginUser.getPhoneNumber());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //判断密码是否相等
        if (!encoder.matches(loginUser.getPassword(),getUser.getPassword())) {
            return JsonResponse.failure("密码输入错误！").setCode(400);
        }

        return JsonResponse.success(getUser);
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "用户注册接口")
    @ResponseBody
    public JsonResponse register(@RequestBody LoginUser loginUser) {

        log.info("注册账号：" + loginUser.getPhoneNumber());

        if (loginUserService.getByPhone(loginUser.getPhoneNumber()) != null){
            return JsonResponse.failure("该手机号已被注册，请更换其他手机号进行注册！").setCode(400);
        }

        //对前端发送密码进行加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(loginUser.getPassword());

        loginUser.setDeleted(true).setPassword(encoded);
        loginUserService.save(loginUser);
        return JsonResponse.success("注册成功！");
    }

    @PostMapping(value = "/modify")
    @ApiOperation(value = "修改用户密码")
    @ResponseBody
    public JsonResponse modify(@RequestBody Modify modify) {

        log.info("修改密码：" + modify.getPhoneNumber());

        LoginUser user = loginUserService.getByPhone(modify.getPhoneNumber());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //判断原密码是否相等，若相等则修改密码，否则返回错误信息
        if (encoder.matches(modify.getOldPass(),user.getPassword())) {
            user.setPassword(encoder.encode(modify.getNewPass()));
            loginUserService.saveOrUpdate(user);
            log.info("修改密码成功");
            return JsonResponse.success("密码修改成功！");
        }

        log.error("修改密码失败");
        return JsonResponse.failure("原密码错误").setCode(400);
    }

    //登录获取验证码
    @RequestMapping("/getSysManageLoginCode")
    @ResponseBody
    public String getSysManageLoginCode(HttpServletResponse response,
                                        HttpServletRequest request) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response,"imagecode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //验证码校验
    @RequestMapping(value = "/checkimagecode")
    @ResponseBody
    public boolean checkTcode(HttpServletRequest request, HttpServletResponse response) {
        String validateCode = request.getParameter("validateCode");
        validateCode = validateCode.toUpperCase();

        String code = null;
        //获取cookie里面的验证码信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("imagecode".equals(cookie.getName())) {
                code = cookie.getValue();
                break;
            }
        }
        //2:判断验证码是否正确
        if(!StringUtils.isEmpty(validateCode) && validateCode.equals(code)){
            return true;
        }
        return false;
    }


    @PostMapping("/logout")
    @ApiOperation(value = "用户退出登录接口")
    public JsonResponse<String> logout(HttpServletRequest request){

        request.getSession().removeAttribute("LoginUser");
        return JsonResponse.success("退出成功");
    }




}
