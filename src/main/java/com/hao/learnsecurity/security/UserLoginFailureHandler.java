package com.hao.learnsecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.learnsecurity.common.Result;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Security 登入成功
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

        if (e instanceof LockedException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed("账户被锁定，请联系管理员!")));
        } else if (e instanceof CredentialsExpiredException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed("密码过期，请联系管理员!")));
        } else if (e instanceof AccountExpiredException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed("账户过期，请联系管理员!")));
        } else if (e instanceof DisabledException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed( "账户被禁用，请联系管理员!")));
        } else if (e instanceof BadCredentialsException) {
            out.write(new ObjectMapper().writeValueAsString(Result.failed("用户名或者密码输入错误，请重新输入!")));
        }
        out.flush();
        out.close();
    }
}
