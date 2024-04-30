package com.nikou.newsforum.controller;

import com.nikou.newsforum.pojo.Result;
import com.nikou.newsforum.utils.AliOssUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String ori = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString()+ori.substring(ori.lastIndexOf("."));
        //file.transferTo(new File("F:\\JAVAEE\\NewsForum\\res\\"+filename));
        String url = AliOssUtil.aliUploadFile(filename,file.getInputStream());
        return Result.success(url);
    }
}
