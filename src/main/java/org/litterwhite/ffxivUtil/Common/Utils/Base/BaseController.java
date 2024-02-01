package org.litterwhite.ffxivUtil.Common.Utils.Base;

import org.litterwhite.ffxivUtil.Common.Utils.AjaxResult;

public class BaseController {
    public AjaxResult requestSuccess(String msg){
        return new AjaxResult(200,msg);
    }

    public AjaxResult requestError(String msg){
        return new AjaxResult(500,msg);
    }
}
