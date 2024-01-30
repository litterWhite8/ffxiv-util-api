package org.litterwhite.ffxivUtil.System.Mapper;

import org.litterwhite.ffxivUtil.System.Pojo.SysUser;

public interface SysUserMapper {
    int insertSysUser(SysUser sysUser);

    SysUser selectSysUserByUserName(String userName);
}
