package com.hao.learnsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.learnsecurity.entity.SysUserRole;
import com.hao.learnsecurity.mapper.SysUserRoleMapper;
import com.hao.learnsecurity.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户与角色关系表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2021-05-10 13:57:15
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
