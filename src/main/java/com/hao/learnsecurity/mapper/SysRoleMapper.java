package com.hao.learnsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.learnsecurity.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-10 13:57:10
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
