package org.litterwhite.ffxivUtil.System.Controller;


import org.litterwhite.ffxivUtil.Common.JWT.JwtTokenUtil;
import org.litterwhite.ffxivUtil.System.Pojo.SysUser;
import org.litterwhite.ffxivUtil.System.Service.RedisService;
import org.litterwhite.ffxivUtil.System.Service.SysUserService;
import org.litterwhite.ffxivUtil.Common.Utils.AjaxResult;
import org.litterwhite.ffxivUtil.Common.Utils.Base.BaseController;
import org.litterwhite.ffxivUtil.Common.Utils.DateUtils;
import org.litterwhite.ffxivUtil.Common.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/system/auth")
public class SysAuthController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @GetMapping("/login")
    public AjaxResult login(HttpServletResponse response,String userName, String password){
        SysUser user = sysUserService.selectSysUserByUserName(userName);
        if(user == null){
            return requestError("用户不存在");
        }
        if(user.getPassword().equals(PasswordUtil.getMd5Encryption(password))){
            String token = jwtUtil.generateToken(userName);
            //添加token至cookie
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            return requestSuccess("登录成功");
        }else {
            return requestError("密码错误");
        }

    }
}
