package com.hao.learnsecurity.security;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.learnsecurity.common.CommonConstants;
import com.hao.learnsecurity.common.config.JWTConfig;
import com.hao.learnsecurity.common.utils.JWTUtil;
import com.hao.learnsecurity.common.utils.RedisUtil;
import com.hao.learnsecurity.entity.SysUser;
import com.hao.learnsecurity.mapper.SysUserMapper;
import com.hao.learnsecurity.security.enity.SelfUserEntity;
import com.hao.learnsecurity.security.service.UserLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;

/**
 * @author wang hao
 * @since 2021/5/10 15:45
 */
@Slf4j
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserLoginServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filtecrChain) throws ServletException, IOException {


        // 获取请求头中JWT的Token
        String tokenHeader = httpServletRequest.getHeader(JWTConfig.tokenHeader);

        if (StrUtil.isNotBlank(tokenHeader) ) {
            try {
                // 解析JWT
                Algorithm algorithm = Algorithm.HMAC256(JWTConfig.secret);
                DecodedJWT verifier = JWT.require(algorithm).build().verify(tokenHeader);
                String userInfo = verifier.getClaim("userInfo").asString();
                SelfUserEntity selfUserEntity = JSONUtil.toBean(userInfo, SelfUserEntity.class);
                // 获取用户名
                String username = selfUserEntity.getUsername();
                Long userId=selfUserEntity.getUserId();

                String key = CommonConstants.TOKEN_KEY + selfUserEntity.getUserId();
                // 判断key是否存在 和 token是否相等  如果需要单点登录则需要这个判断
                if (redisUtil.hasKey(key) && redisUtil.get(key).equals(tokenHeader)){

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {
                log.info("Token无效");
                e.printStackTrace();
            }
        }

        filtecrChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
