package com.hao.learnsecurity.common.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hao.learnsecurity.entity.BO.UserBO;

import java.util.Date;

/**
 * jwt的工具类
 *
 * @author wang hao
 * @since 2021/5/10 14:46
 */
public class JWTUtil {


    public final static String secret = "JWTSecret";
    public final static String issuer = "hao-";
    private static final long TOKEN_EXP_TIME = 24 * 3600 * 1000;
    private static final String username = "username";

    public static String createAccessToken(UserBO userBO) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 登陆成功生成JWT
        String token = JWT.create()
                // 放入用户名和用户ID
                .withClaim(username,userBO.getUsername())
                // 签发时间
                .withIssuedAt(now)
                // 签发者
                .withIssuer(issuer)
                // 自定义属性 放入用户拥有权限
                .withClaim("authorities", JSON.toJSONString(userBO.getAuthorities()))
                // 失效时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXP_TIME))
                // 签名算法和密钥
                .sign(algorithm);
        return token;
    }


    public static boolean verify(String token, String username) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .withClaim(JWTUtil.username, username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
