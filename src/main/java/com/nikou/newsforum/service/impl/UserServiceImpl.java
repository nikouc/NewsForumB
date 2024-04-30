package com.nikou.newsforum.service.impl;

import com.nikou.newsforum.mapper.UserMapper;
import com.nikou.newsforum.pojo.User;
import com.nikou.newsforum.service.UserService;
import com.nikou.newsforum.utils.Md5Util;
import com.nikou.newsforum.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //加密处理
        String md5String = Md5Util.encrypt(password);
        //添加
        userMapper.add(username,md5String);
    }

    @Override
    public void userUpdate(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.userUpdate(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.encrypt(newPwd),id);
    }
}
