package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.StoreDetailInfo;

/**
 * Created by kevin on 2016/10/26
 */
public interface DetailActivityView extends BaseView {
    void getData(Cart model);
    void getStroeData(StoreDetailInfo model);
    void changeCartNum(ChangeNum model);
    void collectResult(BaseBean model);
    void clearShopping(BaseBean model);
}
