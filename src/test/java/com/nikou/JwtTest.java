package com.nikou;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");

        //生成jwt
        String token = JWT.create()
                             .withClaim("user",claims)
                             .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//到期时间
                             .sign(Algorithm.HMAC256("nikou"));//密钥

        System.out.println(token);
    }

    @Test
    //解析验证jwt
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTE3MDM3NDN9" +
                ".vBn_4SS1WSIaxB7zDbN97YiECtP098f7I0htisWrGiM";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("nikou")).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claimMap = decodedJWT.getClaims();
        System.out.println(claimMap.get("user"));

        //报错:token被篡改或过期，null:密钥不正确
    }
}
