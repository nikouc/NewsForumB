package com.nikou.newsforum.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;

    private String username;
    private String password  ;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname  ;

    @NotEmpty
    @Email
    private String email     ;

    private String userPic  ;
    private LocalDateTime creatTime ;
    private LocalDateTime updateTime ;

    @JsonIgnore    //让springmvc把当前对象转换成json字符串的时候，忽略password
    public String getPassword() {
        return password;
    }
}
