package com.hao.learnsecurity.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.learnsecurity.entity.SysRole;
import com.hao.learnsecurity.entity.SysUser;
import com.hao.learnsecurity.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wang hao
 * @since 2021/5/10 14:44
 */
@Component
@Slf4j
public class UserLoginService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StrUtil.isBlank(s)) {
            log.error("用户为空");
            throw new RuntimeException("用户为空");
        }

        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, s));
        if (ObjectUtil.isEmpty(sysUser.getUserId())) {
            log.error("不存在该用户");
            throw new RuntimeException("不存在该用户");
        }

        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRole> sysRoleEntityList = sysUserMapper.selectSysRoleByUserId(sysUser.getUserId());
        for (SysRole sysRoleEntity: sysRoleEntityList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }


        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }

}
