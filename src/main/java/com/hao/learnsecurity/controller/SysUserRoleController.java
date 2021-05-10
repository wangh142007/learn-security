package com.hao.learnsecurity.controller;

import com.hao.learnsecurity.service.SysUserRoleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-05-10 13:57:15
 */
@Api(tags = "用户与角色关系表(SysUserRole)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("sysUserRole")
public class SysUserRoleController {
    @Autowired
    private final SysUserRoleService sysUserRoleService;

}
