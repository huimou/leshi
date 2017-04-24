package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.VipSubmitBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface TxActivityView extends BaseView {

    void showVip(ShowVipBean getInfo);

    void vipTxApply(VipSubmitBean bean);
}
