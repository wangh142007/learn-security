package com.hao.learnsecurity.mapper;

import com.hao.learnsecurity.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限表(SysMenu)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-10 13:56:26
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

}
