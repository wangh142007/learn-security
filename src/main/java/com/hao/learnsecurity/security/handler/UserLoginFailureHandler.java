package com.hao.learnsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.learnsecurity.common.Result;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Security 登入失败
 *
 * @author wang hao
 * @since 2021/5/10 14:27
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //登录失败直接返回错误码
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        if (e instanceof UsernameNotFoundException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed("用户名不存在")));
            out.flush();
            out.close();
        }
        if (e instanceof LockedException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed("用户被冻结")));
            out.flush();
            out.close();
        }
        if (e instanceof BadCredentialsException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed("用户名密码不正确")));
            out.flush();
            out.close();
        }

        out.write(new ObjectMapper().writeValueAsString(Result.failed("登录失败")));
        out.flush();
        out.close();
    }
}
