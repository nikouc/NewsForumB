package com.nikou.newsforum.exception;


import com.nikou.newsforum.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        if(e.getMessage().indexOf("login")!=-1){
            if(e.getMessage().indexOf("需要匹配正则表达式\"^\\S{5,16}$\"")!=-1){
                return Result.error("用户名或密码错误");
            }
        }
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage() :"操作失败");
    }
}
