package com.wangjia.yijiale.iview;


import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.CollectionList;

/**
 * Created by kevin on 2016/10/26
 */
public interface MyCollectionActivityView extends BaseView {
    void getData(CollectionList model);

    void collectGoodsResult(BaseBean getInfo);
}
