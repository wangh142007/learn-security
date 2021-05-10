package com.hao.learnsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.learnsecurity.entity.SysUser;
import com.hao.learnsecurity.mapper.SysUserMapper;
import com.hao.learnsecurity.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2021-05-10 13:57:15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
