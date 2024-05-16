package com.example.mybatisplus.common.exception;


import lombok.Getter;

/**
 * 自定义异常处理
 */
@Getter
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}


