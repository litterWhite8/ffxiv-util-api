package org.litterwhite.ffxivUtil.System.Service;

import org.litterwhite.ffxivUtil.System.Pojo.SysUser;

public interface SysUserService {
    int insertUser(SysUser sysUser);

    SysUser selectSysUserByUserName(String userName);
}
