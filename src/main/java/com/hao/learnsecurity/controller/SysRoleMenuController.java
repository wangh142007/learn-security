package com.hao.learnsecurity.controller;

import com.hao.learnsecurity.service.SysRoleMenuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-05-10 13:57:14
 */
@Api(tags = "角色与权限关系表(SysRoleMenu)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private final SysRoleMenuService sysRoleMenuService;

}
