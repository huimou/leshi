package com.wangjia.yijiale.iview;


import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.StoreCollectionList;

/**
 * Created by kevin on 2016/10/26
 */
public interface MyStoreCollectionActivityView extends BaseView {
    void getData(StoreCollectionList model);
    void collectResult(BaseBean model);
}
