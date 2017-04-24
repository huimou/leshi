package com.wangjia.yijiale.iview;

import com.wangjia.yijiale.entity.Banner;
import com.wangjia.yijiale.entity.Classify;
import com.wangjia.yijiale.entity.NearStoreList;
import com.wangjia.yijiale.entity.StoreAreaList;
import com.wangjia.yijiale.entity.StoreList;
import com.wangjia.yijiale.entity.StroeClassifyBean;

/**
 * Created by kevin on 2016/10/26
 */
public interface HomeFragmentBannerView extends BaseView {
    void getBannerData(Banner model);
    void getStoreClassify(StroeClassifyBean model);
    void getCenterBannerData(Banner model);
    void getClassifyData(Classify model);
    void getStoreListData(StoreList model);
    void getNearStoreListData(NearStoreList model);
    void getStoreAareaList(StoreAreaList model);
//    void getShiList(AreaList model);
}
