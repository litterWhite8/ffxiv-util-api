package org.litterwhite.ffxivUtil.System.Pojo;


import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private Long id;

    private String userName;

    private String nickName;

    private String password;

    private Date createTime;

}
