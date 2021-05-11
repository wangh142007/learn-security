package com.hao.learnsecurity.common.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.hao.learnsecurity.common.config.JWTConfig;
import com.hao.learnsecurity.security.enity.SelfUserEntity;

import java.util.Date;

/**
 * jwt的工具类
 *
 * @author wang hao
 * @since 2021/5/10 14:46
 */
public class JWTUtil {



    public static String createAccessToken(SelfUserEntity selfUserEntity) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(JWTConfig.secret);
        // 登陆成功生成JWT
        String token = JWT.create()
                // 签发时间
                .withIssuedAt(now)
                // 失效时间
                .withExpiresAt(new Date(now.getTime() + JWTConfig.expiration))
                // 签发者
                .withSubject("hao")
                // 自定义属性 放入用户拥有权限
                .withClaim("userInfo", JSON.toJSONString(selfUserEntity))
                // 签名算法和密钥
                .sign(algorithm);
        return token;
    }




//    public static boolean verify(String token, String username) {
//
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(JWTConfig.secret);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("hao")
//                    .withClaim(JWTUtil.username, username)
//                    .build();
//            verifier.verify(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }


}
