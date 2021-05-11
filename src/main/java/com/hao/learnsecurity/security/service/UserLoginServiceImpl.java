package com.hao.learnsecurity.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.learnsecurity.entity.SysUser;
import com.hao.learnsecurity.mapper.SysUserMapper;
import com.hao.learnsecurity.security.enity.SelfUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author wang hao
 * @since 2021/5/10 14:44
 */
@Slf4j
@Component
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, s));
        //组装参数
        if (sysUser != null) {
            // 组装参数
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(sysUser, selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }

}
