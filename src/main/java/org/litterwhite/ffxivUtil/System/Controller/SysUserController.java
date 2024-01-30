package org.litterwhite.ffxivUtil.System.Controller;

import org.litterwhite.ffxivUtil.System.Pojo.SysUser;
import org.litterwhite.ffxivUtil.System.Service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/system/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;


    @PostMapping("/create-user")
    public int createUser(@RequestBody SysUser sysUser){
        return sysUserService.insertUser(sysUser);
    }
}
