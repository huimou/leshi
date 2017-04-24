package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.AddAddress;
import com.wangjia.yijiale.entity.BaseBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface AddAddressActivityView extends BaseView {
    void getData(AddAddress model);
    void getUpdateAddress(BaseBean model);

}
