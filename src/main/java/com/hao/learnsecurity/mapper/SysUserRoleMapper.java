package com.hao.learnsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.learnsecurity.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色关系表(SysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-10 13:57:15
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
