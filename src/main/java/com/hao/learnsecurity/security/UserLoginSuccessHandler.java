package com.hao.learnsecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.learnsecurity.common.CommonConstants;
import com.hao.learnsecurity.common.Result;
import com.hao.learnsecurity.common.utils.JWTUtil;
import com.hao.learnsecurity.common.utils.RedisUtil;
import com.hao.learnsecurity.entity.BO.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserBO userBO = UserBO.builder().username(userDetails.getUsername()).authorities(userDetails.getAuthorities().toString()).build();
        String accessToken = JWTUtil.createAccessToken(userBO);

        redisUtil.set(CommonConstants.TOKEN_KEY + userDetails.getUsername(), accessToken);

        PrintWriter out = httpServletResponse.getWriter();
        // 封装返回参数
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("username", userBO.getUsername());
        resultData.put("authorities", userBO.getAuthorities());
        resultData.put("token", accessToken);
        out.write(new ObjectMapper().writeValueAsString(Result.ok(resultData, "登录成功")));
        out.flush();
        out.close();
    }
}
