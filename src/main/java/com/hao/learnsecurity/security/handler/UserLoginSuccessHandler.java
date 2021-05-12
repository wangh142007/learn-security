package com.hao.learnsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.learnsecurity.common.CommonConstants;
import com.hao.learnsecurity.common.Result;
import com.hao.learnsecurity.common.utils.JWTUtil;
import com.hao.learnsecurity.common.utils.RedisUtil;
import com.hao.learnsecurity.security.enity.SelfUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Security 登录成功
 *
 * @author wang hao
 * @since 2021/5/10 14:27
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();


        String accessToken = JWTUtil.createAccessToken(selfUserEntity);

        redisUtil.set(CommonConstants.TOKEN_KEY + selfUserEntity.getUserId(), accessToken);

        PrintWriter out = httpServletResponse.getWriter();
        // 封装返回参数
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("username", selfUserEntity.getUsername());
        resultData.put("authorities", selfUserEntity.getAuthorities());
        resultData.put("token", accessToken);
        out.write(new ObjectMapper().writeValueAsString(Result.ok(resultData, "登录成功")));
        out.flush();
        out.close();
    }
}
