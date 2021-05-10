package com.hao.learnsecurity.security;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.learnsecurity.common.utils.JWTUtil;
import com.hao.learnsecurity.entity.SysUser;
import com.hao.learnsecurity.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wang hao
 * @since 2021/5/10 15:45
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserLoginService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filtecrChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader("token");//获取header中的验证信息
        String username = httpServletRequest.getHeader("username");//获取header中的验证信息

        //判断token是否为空
        if (StrUtil.isNotBlank(authHeader)){
            //token 是否是正确的的
            if (JWTUtil.verify(authHeader,username)){
                //判断这个账号存在吗
                SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
                if (StrUtil.isNotBlank(String.valueOf(sysUser.getUserId()))){

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(sysUser, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }
        }

        filtecrChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
