package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.UpdatePasswordBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface UpdatePasswordActivityView extends BaseView {
    void getData(UpdatePasswordBean model);

    /**
     * 获取验证码
     * @param model
     */
    void getCode(CodeBean model);
}
