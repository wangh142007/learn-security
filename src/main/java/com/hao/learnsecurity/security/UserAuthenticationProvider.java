package com.hao.learnsecurity.security;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.learnsecurity.entity.SysRole;
import com.hao.learnsecurity.entity.SysUser;
import com.hao.learnsecurity.mapper.SysUserMapper;
import com.hao.learnsecurity.security.enity.SelfUserEntity;
import com.hao.learnsecurity.security.service.UserLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wang hao
 * @since 2021/5/11 9:31
 */
@Slf4j
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserLoginServiceImpl userLoginService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();

        // 查询用户是否存在
        SelfUserEntity userInfo = (SelfUserEntity) userLoginService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }


        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRole> sysRoleEntityList = sysUserMapper.selectSysRoleByUserId(userInfo.getUserId());
        for (SysRole sysRoleEntity : sysRoleEntityList) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }

        userInfo.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
