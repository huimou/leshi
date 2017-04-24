package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.BaseBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface AddressManageActivityView extends BaseView {
    void getData(AddressManageList model);
    void getDeleteAddress(BaseBean model);
//    void getUpdateAddress(BaseBean model);
}
