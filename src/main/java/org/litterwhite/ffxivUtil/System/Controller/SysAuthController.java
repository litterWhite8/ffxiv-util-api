package org.litterwhite.ffxivUtil.System.Controller;


import org.litterwhite.ffxivUtil.System.Pojo.SysUser;
import org.litterwhite.ffxivUtil.System.Service.SysUserService;
import org.litterwhite.ffxivUtil.Utils.AjaxResult;
import org.litterwhite.ffxivUtil.Utils.Base.BaseController;
import org.litterwhite.ffxivUtil.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/auth")
public class SysAuthController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/login")
    public AjaxResult login(String userName, String password){
        SysUser user = sysUserService.selectSysUserByUserName(userName);
        if(user == null){
            return requestError("用户不存在");
        }
        if(user.getPassword().equals(PasswordUtil.getMd5Encryption(password))){
            return requestSuccess("登录成功");
        }else {
            return requestError("密码错误");
        }

    }
}
