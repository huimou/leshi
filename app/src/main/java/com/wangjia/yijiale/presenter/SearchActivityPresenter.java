package com.wangjia.yijiale.presenter;

/**
 * Created by kevin on 2016/10/26
 */
public interface SearchActivityPresenter extends BasePresenter {
    void getData(int now_page, String keyword, String area_info, String sc_id);
}
