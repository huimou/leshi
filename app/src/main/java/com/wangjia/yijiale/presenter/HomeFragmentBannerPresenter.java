package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface HomeFragmentBannerPresenter extends BasePresenter {
    void getBannerData();
    void getCenterBannerData();
    void getStoreClassify();
    void getClassifyData();
    void getStoreListData(String keyword, String area_info, String now_page);
    void getShengList();
//    void getShiList(String area_id);
    void nearStoreList(String lng,String lat,int sort, int page_size , String sc_id ,
                       String child_sc_id , int page_now,String province,String city) ;
}
