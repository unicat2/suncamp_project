package com.example.mybatisplus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    //    @RequestMapping("/console")
//    public String console(){
//        return "/views/home/console";
//    }

    @RequestMapping("/")
    public String index(){
        return "/login";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/register")
    public String register(){
        return "/register";
    }

    @RequestMapping("/home")
    public String home(){
        return "/home";
    }

    @RequestMapping("/request")
    public String request(){
        return "word/author";
    }

    @RequestMapping("/try")
    public String tryf(){
//        return "/top";
        return "word/try";
    }
    //
    @RequestMapping("/forget")
    public String forget(){return "/forget";}

    @RequestMapping("/adminSystem")
    public  String adminsystem(){return "/adminSystem";}

    @RequestMapping("/declare")
    public String declare() {return "/declare";}

    @RequestMapping("/content")
    public String content() {return "/content";}

    @RequestMapping("/authorImf")
    public String authorImf() {return "/material/author/authorImf";}

    @RequestMapping("/top")
    public String top() {return "/top";}

    @RequestMapping("/workImf")
    public String workImf() {return "/material/work/workImf";}

    @RequestMapping("/fileUpload")
    public String idImf() {return "/material/upload/fileUpload";}

    @RequestMapping("/formDisplay")
    public String form() {return "/form";}

}
