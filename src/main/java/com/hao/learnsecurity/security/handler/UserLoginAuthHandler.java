package com.hao.learnsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.learnsecurity.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证用户是否登录
 *
 * @author wang hao
 * @since 2021/5/10 14:39
 */
@Component
public class UserLoginAuthHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(Result.failed(false,"尚未登录，请先登录")));
        out.flush();
        out.close();

    }
}
