package com.nikou;

import com.nikou.newsforum.NewsForumApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = NewsForumApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis(){
        ValueOperations<String,String> vo = stringRedisTemplate.opsForValue();
        vo.set("username","zhangsan");
        vo.set("id","1",15, TimeUnit.SECONDS);
    }

    @Test
    public void testGet(){
        ValueOperations<String,String> vo = stringRedisTemplate.opsForValue();
        System.out.println(vo.get("username"));
        System.out.println(vo.get("id"));
    }
}
