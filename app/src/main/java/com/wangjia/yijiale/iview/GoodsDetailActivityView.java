package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.GoodsDetailInfo;

/**
 * Created by kevin on 2016/10/26
 */
public interface GoodsDetailActivityView extends BaseView {
    void getGoodsData(GoodsDetailInfo model);
    void collectGoodsResult(BaseBean model);
    void getData(Cart model);

    void changeCartNum(ChangeNum getInfo);

    void changeNum(ChangeNum getInfo);

    void clearShopping(BaseBean getInfo);
}
