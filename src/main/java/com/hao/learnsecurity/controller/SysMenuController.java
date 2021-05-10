package com.hao.learnsecurity.controller;

import com.hao.learnsecurity.entity.SysMenu;
import com.hao.learnsecurity.service.SysMenuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-05-10 13:56:29
 */
@Api(tags = "权限表(SysMenu)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("sysMenu")
public class SysMenuController {
    @Autowired
    private final SysMenuService sysMenuService;

}
