package com.nikou.newsforum.controller;

import com.nikou.newsforum.pojo.Article;
import com.nikou.newsforum.pojo.PageBean;
import com.nikou.newsforum.pojo.Result;
import com.nikou.newsforum.service.ArticleService;
import com.nikou.newsforum.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/){
//
////        try {
////            Map<String,Object> claims = JwtUtil.parseToken(token);
////        } catch (Exception e) {
////            //响应状态码
////            response.setStatus(401);
////            return Result.error("未登录");
////        }
//        return Result.success("这里是文章数据.......");
//    }

    //添加文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    //列表分页查询
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,
                                          Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false) String  state){
        PageBean<Article> pageBean = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }

    //更新文章
    @PutMapping
    public Result updateArticle(@RequestBody @Validated(Article.update.class) Article article){
        articleService.updateArticle(article);
        return  Result.success("更新成功");
    }

    //删除文章
    @GetMapping("/delete")
    public Result delete(@RequestParam @Validated(Article.delete.class) Integer id){
        articleService.delete(id);
        return Result.success("成功删除");
    }
}
