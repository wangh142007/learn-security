package com.hao.learnsecurity.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-05-10 13:57:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends Model {
    private static final long serialVersionUID = -83610795196702774L;

    @TableField("role_id")
    private Long roleId;

    @TableField("role_name")
    private String roleName;

}
