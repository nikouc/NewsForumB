package com.nikou.newsforum.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nikou.newsforum.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    @NotNull(groups = {update.class,delete.class})
    private Integer id             ;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title          ;
    @NotEmpty
    private String content        ;
    @NotEmpty
    @URL
    private String coverImg      ;
    @State
    private String state          ;
    @NotNull
    private Integer categoryId    ;
    private Integer createUserid  ;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime    ;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime    ;

    public interface update extends Default{}
    public interface delete{}
}
