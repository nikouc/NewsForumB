package com.nikou.newsforum.mapper;

import com.nikou.newsforum.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article (title,content,cover_img,state,category_id,create_userid,create_time,update_time) " +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUserid},#{createTime},#{updateTime});")
    void add(Article article);


    List<Article> list(Integer userid, Integer categoryId, String state);

    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=#{updateTime}" +
            " where id = #{id};")
    void updateArticle(Article article);

    @Delete("delete from article where id = #{id} and create_userid=#{userid};")
    void delete(Integer id, Integer userid);

    @Select("select * from article where id=#{id};")
    Article search(Integer id);
}
