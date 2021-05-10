package com.hao.learnsecurity.controller;

import com.hao.learnsecurity.service.SysRoleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-05-10 13:57:12
 */
@Api(tags = "角色表(SysRole)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("sysRole")
public class SysRoleController {
    @Autowired
    private final SysRoleService sysRoleService;

}
