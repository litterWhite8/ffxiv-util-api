package org.litterwhite.ffxivUtil.Common.Utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Objects;


/**
  *ajax返回类
  */
@Data
public class AjaxResult extends HashMap<Object,Object> {

    public AjaxResult(int code,String msg){
        this.put("code",code);
        this.put("msg",msg);
    }


}
