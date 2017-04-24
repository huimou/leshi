package com.wangjia.yijiale.presenter;

import java.io.File;

/**
 * Created by kevin on 2016/10/26
 */
public interface UpdateZlActivityPresenter extends BasePresenter {
    void getData(File member_avatar, int member_sex, int member_age, String member_idcard, String member_truename,String member_name);
}
