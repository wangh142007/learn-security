package com.hao.learnsecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.learnsecurity.common.CommonConstants;
import com.hao.learnsecurity.common.Result;
import com.hao.learnsecurity.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Security 登出
 *
 * @author wang hao
 * @since 2021/5/10 14:27
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redisUtil.del(CommonConstants.TOKEN_KEY + userDetails.getUsername());
        out.write(new ObjectMapper().writeValueAsString(Result.ok(true, "登出成功")));
        out.flush();
        out.close();
    }
}
