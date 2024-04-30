package com.nikou.newsforum.mapper;

import com.nikou.newsforum.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,creat_time,update_time)"+
            "values(#{username},#{md5String},now(),now())")
    void add(String username, String md5String);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void userUpdate(User user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    @Update("update user set password=#{encrypt},update_time=now() where id=#{id}")
    void updatePwd(String encrypt, Integer id);
}
