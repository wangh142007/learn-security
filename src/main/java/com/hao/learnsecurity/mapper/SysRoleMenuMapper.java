package com.hao.learnsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.learnsecurity.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色与权限关系表(SysRoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-10 13:57:14
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}
