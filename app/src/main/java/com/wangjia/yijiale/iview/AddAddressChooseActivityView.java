package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.AddAddress;
import com.wangjia.yijiale.entity.AreaList;

/**
 * Created by kevin on 2016/10/26
 */
public interface AddAddressChooseActivityView extends BaseView {
    void getShengList(AreaList model);
    void getShiList(AreaList model);
    void getQuList(AreaList model);
}
