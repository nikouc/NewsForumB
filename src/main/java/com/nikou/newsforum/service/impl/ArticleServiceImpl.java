package com.nikou.newsforum.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nikou.newsforum.mapper.ArticleMapper;
import com.nikou.newsforum.pojo.Article;
import com.nikou.newsforum.pojo.PageBean;
import com.nikou.newsforum.service.ArticleService;
import com.nikou.newsforum.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //补充属性
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        article.setCreateUserid(userid);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();

        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        //调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userid,categoryId,state);
        //Page中提供了方法，可以获取PageHelper分页查询后 得到的总记录条数和当前页面数据
        Page<Article> p = (Page<Article>) as;

        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public void updateArticle(Article article) {
        Article searchArt = articleMapper.search(article.getId());
        if (searchArt==null){
            throw new RuntimeException("更新的文件不存在");
        }
        //当前时间
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateArticle(article);
    }

    @Override
    public void delete(Integer id) {
        //检查文件是否存在
        Article searchArt = articleMapper.search(id);
        if (searchArt==null){
            throw new RuntimeException("删除的文件不存在");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        articleMapper.delete(id,userid);
    }
}
