package com.hao.learnsecurity.controller;

import com.hao.learnsecurity.service.SysUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-05-10 13:57:15
 */
@Api(tags = "系统用户表(SysUser)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("sysUser")
public class SysUserController {
    @Autowired
    private final SysUserService sysUserService;

}
