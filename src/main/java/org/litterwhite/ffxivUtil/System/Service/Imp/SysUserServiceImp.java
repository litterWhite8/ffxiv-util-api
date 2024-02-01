package org.litterwhite.ffxivUtil.System.Service.Imp;

import org.litterwhite.ffxivUtil.System.Mapper.SysUserMapper;
import org.litterwhite.ffxivUtil.System.Pojo.SysUser;
import org.litterwhite.ffxivUtil.System.Service.SysUserService;
import org.litterwhite.ffxivUtil.Common.Utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysUserServiceImp implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public int insertUser(SysUser sysUser){
        sysUser.setCreateTime(new Date());
        sysUser.setPassword(PasswordUtil.getMd5Encryption(sysUser.getPassword()));
        return sysUserMapper.insertSysUser(sysUser);
    }

    @Override
    public SysUser selectSysUserByUserName(String userName){
        return sysUserMapper.selectSysUserByUserName(userName);
    }
}
