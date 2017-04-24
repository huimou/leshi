package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.CommentListBean;
import com.wangjia.yijiale.entity.StoreGoodsList;

/**
 * Created by kevin on 2016/10/26
 */
public interface ProductListFragmentView extends BaseView {
    void getData(StoreGoodsList model);
    void changeNum(ChangeNum model);
    void getStoreCommenrt(CommentListBean getInfo);
}
