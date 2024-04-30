package com.nikou.newsforum.service;

import com.nikou.newsforum.pojo.Article;
import com.nikou.newsforum.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

public interface ArticleService {
    //添加文章
    void add(Article article);

    //条件查询文章
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //更新文章内容
    void updateArticle(Article article);

    //删除文章
    void delete(Integer id);
}
