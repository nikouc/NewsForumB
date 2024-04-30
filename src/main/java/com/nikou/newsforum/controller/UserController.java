package com.nikou.newsforum.controller;

import com.nikou.newsforum.pojo.Result;
import com.nikou.newsforum.pojo.User;
import com.nikou.newsforum.service.UserService;
import com.nikou.newsforum.utils.JwtUtil;
import com.nikou.newsforum.utils.Md5Util;
import com.nikou.newsforum.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
//        if (username!=null && username.length()>=5 && username.length()<=16 &&
//                password!=null && password.length()>=5 && password.length()<=16){
            //查询用户
            User user = userService.findByUserName(username);
            if (user==null){
                //注册
                userService.register(username,password);
                return Result.success();
            }else {
                return Result.error("用户名已被占用!");
            }
//        }else {
//            return Result.error("用户名或密码不合法！");
//        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断用户是否存在
        if (loginUser==null){
            return Result.error("用户名错误");
        }
        //判断密码是否正确
        if (Md5Util.compare(password,loginUser.getPassword())){

            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            //把token放入redis中
            ValueOperations<String,String> vo = stringRedisTemplate.opsForValue();
            vo.set(token,token,6, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误!");
    }

    @GetMapping("/userInfo")
    public  Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
//        Map<String,Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return  Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.userUpdate(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        //校验参数
        String oldPwd = params.get("oldpwd");
        String newPwd = params.get("newpwd");
        String rePwd = params.get("repwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService比对
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        if (!Md5Util.compare(oldPwd,user.getPassword())){
            return Result.error("原密码填写不正确");
        }

        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一致");
        }

        userService.updatePwd(newPwd);
        //删除redis中对应的token
        ValueOperations<String,String> vo = stringRedisTemplate.opsForValue();
        vo.getOperations().delete(token);
        return Result.success();
    }
}
