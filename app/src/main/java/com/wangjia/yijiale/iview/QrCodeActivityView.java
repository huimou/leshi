package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.QrCode;

/**
 * Created by kevin on 2016/10/26
 */
public interface QrCodeActivityView extends BaseView {
    void shop_code(QrCode getInfo);

    void payMoneyForStore(BaseBean baseBean);
}
