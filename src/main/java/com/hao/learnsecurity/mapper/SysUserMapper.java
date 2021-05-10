package com.hao.learnsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.learnsecurity.entity.SysMenu;
import com.hao.learnsecurity.entity.SysRole;
import com.hao.learnsecurity.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-10 13:57:15
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户ID查询角色集合
     * @param userId 用户ID
     * @return 角色名集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);

    /**
     * 通过用户ID查询权限集合
     * @param userId 用户ID
     * @return 角色名集合
     */
    List<SysMenu> selectSysMenuByUserId(Long userId);



}
