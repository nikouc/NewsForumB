package com.nikou.newsforum.utils;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;


public class Md5Util extends DigestUtils{

    //加密
    public static String encrypt(String s){
        //String stiring = new BCryptPasswordEncoder().encode("123");

        return md5DigestAsHex(s.getBytes());
    }

    //密码对比
    public static boolean compare(String de,String en){//未加密,加密
        if (encrypt(de).equals(en)){
            return true;
        }else {
            return false;
        }
    }
}
