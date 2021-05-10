package com.hao.learnsecurity.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-05-10 13:56:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends Model {
    private static final long serialVersionUID = 775931999853249056L;

    @TableField("menu_id")
    private Long menuId;

    @TableField("name")
    private String name;

    @TableField("permission")
    private String permission;

}
