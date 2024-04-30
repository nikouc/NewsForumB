package com.nikou.newsforum.mapper;

import com.nikou.newsforum.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into category (category_name,category_alias,create_user,creat_time,update_time) " +
            "values (#{categoryName},#{categoryAlias},#{createUser},#{creatTime},#{updateTime});")
    void add(Category category);

    //查询所有
    @Select("select * from category where create_user = #{userid}")
    List<Category> list(Integer userid);

    @Select("select * from category where id = #{id}")
    Category findById(Integer id);

    @Update("update category set category_name = #{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id = #{id};")
    void update(Category category);

    //删除
    @Delete("delete from category where id = #{id};")
    void deleteById(Integer id);
}
