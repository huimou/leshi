package com.wangjia.yijiale.api;

import com.wangjia.yijiale.entity.AboutBean;
import com.wangjia.yijiale.entity.AddAddress;
import com.wangjia.yijiale.entity.AddressManageList;
import com.wangjia.yijiale.entity.AreaList;
import com.wangjia.yijiale.entity.Banner;
import com.wangjia.yijiale.entity.BaseBean;
import com.wangjia.yijiale.entity.Cart;
import com.wangjia.yijiale.entity.ChangeNum;
import com.wangjia.yijiale.entity.Classify;
import com.wangjia.yijiale.entity.CodeBean;
import com.wangjia.yijiale.entity.CollectionList;
import com.wangjia.yijiale.entity.CommentListBean;
import com.wangjia.yijiale.entity.GoodsDetailInfo;
import com.wangjia.yijiale.entity.Login;
import com.wangjia.yijiale.entity.MyOrder;
import com.wangjia.yijiale.entity.MyStoreDetail;
import com.wangjia.yijiale.entity.NearStoreList;
import com.wangjia.yijiale.entity.OrderDetails;
import com.wangjia.yijiale.entity.Register;
import com.wangjia.yijiale.entity.SearchShopBean;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.StoreAreaList;
import com.wangjia.yijiale.entity.StoreCollectionList;
import com.wangjia.yijiale.entity.StoreDetail;
import com.wangjia.yijiale.entity.StoreDetailInfo;
import com.wangjia.yijiale.entity.StoreGoodsList;
import com.wangjia.yijiale.entity.StoreList;
import com.wangjia.yijiale.entity.StroeClassifyBean;
import com.wangjia.yijiale.entity.SubmitOrderBean;
import com.wangjia.yijiale.entity.SubmitSteOneBean;
import com.wangjia.yijiale.entity.SubmitSteTwoBean;
import com.wangjia.yijiale.entity.UpdatePasswordBean;
import com.wangjia.yijiale.entity.VipSubmitBean;
import com.wangjia.yijiale.entity.ZhifuShiWuBean;

import java.io.File;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by kevin on 2016/10/26
 */
public interface BaseApi {

    //注册
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=api&op=registerapp")
    Observable<Register> register(@Field("member_mobile") String member_mobile,
                                  @Field("member_passwd") String member_password, @Field("verific") String verific);

    //获取验证码
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=api&op=get_sms_code")
    Observable<CodeBean> get_code(@Field("mobile") String mobile, @Field("type") String type);

    //关于我们
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=article&op=index")
    Observable<AboutBean> get_about(@Field("mobile") String mobile);

    //会员充值界面显示
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_fund&op=recharge_ui")
    Observable<ShowVipBean> showVip(@Field("token") String token);

    //实物订单支付界面
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_buy&op=pay")
    Observable<ZhifuShiWuBean> shiwuOrder(@Field("token") String token, @Field("pay_sn") String pay_sn);

    //我的订单列表详情页面
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=apimember&op=member_order_detail")
    Observable<OrderDetails> myOrderDetails(@Field("token") String token, @Field("order_id") int order_id);

    //评价订单
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_evaluate&op=save")
    Observable<BaseBean> commentOrder(@Field("token") String token, @Field("order_id") int order_id, @Field("store_desccredit") int store_desccredit,
                                      @Field("store_servicecredit") int store_servicecredit, @Field("store_deliverycredit") int store_deliverycredit,
                                      @Field("goods[*][score]") int goods);

    //会员充值下单
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_payment&op=recharge_add")
    Observable<SubmitOrderBean> vipSubmitOrder(@Field("token") String token, @Field("pdr_amount") String pdr_amount,
                                             @Field("payment_method") String payment_method);

    //订单确认支付
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_payment&op=pay_new")
    Observable<SubmitOrderBean> orderSubmitPlay(@Field("token") String token, @Field("pay_sn") String pay_sn,
                                                @Field("payment_method") String payment_method);

    //会员提交提现申请
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_fund&op=pdcashadd")
    Observable<VipSubmitBean> vipTxApply(@Field("token") String token, @Field("pdc_amount") String pdc_amount,
                                         @Field("pdc_bank_name") String pdc_bank_name, @Field("pdc_bank_no") String pdc_bank_no,
                                         @Field("pdc_bank_user") String pdc_bank_user, @Field("pdc_bank_province") String pdc_bank_province,
                                         @Field("pdc_bank_city") String pdc_bank_city);


    //登录
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=api&op=login")
    Observable<Login> login(@Field("member_mobile") String member_mobile, @Field("member_passwd") String member_passwd);

    //搜索店铺
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=shop&op=index")
//    int now_page, String keyword, String area_info, String sc_id 店铺分类ID
    Observable<SearchShopBean> search_shop(@Field("now_page") int now_page, @Field("keyword") String keyword,
                                           @Field("area_info") String area_info, @Field("sc_id") String sc_id);

    //修改个人资料
//    @FormUrlEncoded
    @Multipart
    @POST("mobileapp/index.php?act=apimember&op=upMemberInFo")
//    int now_page, String keyword, String area_info, String sc_id 店铺分类ID
    Observable<Login> upMemberInFo(@Field("token") String token, @Part("member_avatar") File member_avatar,
                                   @Field("member_sex") int member_sex, @Field("member_age") int member_age,
                                   @Field("member_idcard") String member_idcard,
                                   @Field("member_truename") String member_truename, @Field("member_nickname") String member_name);

    //修改密码
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=apimember&op=updatePwd")
    Observable<UpdatePasswordBean> update_password(@Field("member_mobile") String member_mobile,
                                                   @Field("member_passwd") String member_passwd,
                                                   @Field("verific") String verific, @Field("token") String token);

    //修改资料
//    @FormUrlEncoded
//    @POST("mobileapp/index.php?act=apimember&op=upMemberInFo")
//    Observable<UpdatePasswordBean> update_password(@Field("member_mobile") String member_mobile, @Field("member_passwd") String member_passwd);

    //首页banner
    @GET("mobile/index.php?act=api&op=home_banner")
    Observable<Banner> home_banner();

    //店铺分类
    @GET("mobileapp/index.php?act=shop_class&op=index")
    Observable<StroeClassifyBean> getStoreClassify();

    //首页中部banner
    @GET("mobileapp/index.php?act=api&op=home_middle_ad")
    Observable<Banner> home_center_banner();

    //    //商品分类
//    @GET("mobile/index.php?act=goods_class&op=get_goods_classify")
//    Observable<Classify> get_goods_classify();


    //首页店铺分类带图标
    @GET("mobileapp/index.php?act=shop_class&op=get_store_list")
    Observable<Classify> get_goods_classify();

    //商家列表
    @FormUrlEncoded
    @POST("mobile/index.php?act=shop&op=index")
    Observable<StoreList> storeList(@Field("keyword") String keyword, @Field("area_info") String area_info, @Field("now_page") String now_page);

    //附近商家列表
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=shop&op=shop_near")
    Observable<NearStoreList> nearStoreList(@Field("lng") String lng, @Field("lat") String lat, @Field("sort") int sort,
                                            @Field("page_size") int page_size, @Field("sc_id") String sc_id,
                                            @Field("child_sc_id") String child_sc_id,
                                            @Field("page_now") int page_now, @Field("province") String province,
                                            @Field("city") String city);


    //添加店铺收藏
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_favorites_store&op=favorites_add")
    Observable<BaseBean> addStoreCollect(@Field("store_id") String store_id, @Field("token") String token);

    //添加商品收藏
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_favorites&op=favorites_add")
    Observable<BaseBean> addGoodsCollect(@Field("goods_id") String goods_id, @Field("token") String token);

    //删除商品收藏
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_favorites&op=favorites_del")
    Observable<BaseBean> delGoodsCollect(@Field("fav_id") String goods_id, @Field("token") String token);

    //删除店铺收藏
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_favorites_store&op=favorites_del")
    Observable<BaseBean> delStoreCollect(@Field("store_id") String store_id, @Field("token") String token);

    //收货地址列表
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_address&op=address_list")
    Observable<AddressManageList> address_list(@Field("token") String token, @Field("just_default") int just_default);

    //获取确认订单数据
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_buy&op=buy_step1")
    Observable<SubmitSteOneBean> submitOrderForOnce(@Field("token") String token, @Field("store_id") String store_id,
                                                    @Field("ifcart") int ifcart, @Field("cart_id") String cart_id,
                                                    @Field("address_id") String address_id);

    //提交订单
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_buy&op=buy_step2")
    Observable<SubmitSteTwoBean> submitOrderForTwo(@Field("token") String token, @Field("goods_id") String goods_id,
                                                   @Field("ifcart") int ifcart, @Field("cart_id") String cart_id,
                                                   @Field("address_id") String address_id, @Field("pay_message") String pay_message,
                                                   @Field("pay_name") String pay_name, @Field("vat_hash") String vat_hash,
                                                   @Field("allow_offpay") String allow_offpay, @Field("offpay_hash") String offpay_hash,
                                                   @Field("offpay_hash_batch") String offpay_hash_batch, @Field("buy_city_id") String buy_city_id);

    //删除收货地址
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_address&op=address_del")
    Observable<BaseBean> getDeleteAddress(@Field("token") String token, @Field("address_id") String address_id);

    //更改收货地址
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_address&op=address_edit")
    Observable<BaseBean> getUpdateAddress(@Field("token") String token, @Field("address_id") String address_id, @Field("true_name") String true_name,
                                          @Field("city_id") String city_id, @Field("area_id") String area_id,
                                          @Field("tel_phone") String tel_phone, @Field("is_default") String is_default,
                                          @Field("post_code") String post_code, @Field("address") String address,
                                          @Field("province_id") String province_id, @Field("area_info") String area_info);

    //收货地址添加
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_address&op=address_add")
    Observable<AddAddress> address_add(@Field("token") String token, @Field("true_name") String true_name,
                                       @Field("city_id") String city_id, @Field("area_id") String area_id,
                                       @Field("tel_phone") String tel_phone, @Field("is_default") String is_default,
                                       @Field("post_code") String post_code, @Field("address") String address,
                                       @Field("province_id") String province_id, @Field("area_info") String area_info);

    //商家入驻-收集商家信息
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=apimember&op=_store_join_info")
    Observable<BaseBean> store_id(@Field("token") String token, @Field("company_name") String company_name,
                                   @Field("company_address_detail") String company_address_detail,
                                  @Field("contacts_name") String contacts_name, @Field("contacts_phone") String contacts_phone
                                 );

    //地区（省市区）
    @FormUrlEncoded
    @POST("mobile/index.php?act=area&op=area_list")
    Observable<AreaList> area_list(@Field("area_id") String area_id);

    //店铺地址列表
    @FormUrlEncoded
    @POST("/mobileapp/index.php?act=area&op=area_list_one")
    Observable<StoreAreaList> store_area_list(@Field("area_id") String area_id);

    //我的收藏列表
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_favorites&op=favorites_list")
    Observable<CollectionList> CollectionList(@Field("token") String token, @Field("now_page") String now_page);

    //收藏店铺列表
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_favorites_store&op=favorites_list")
    Observable<StoreCollectionList> StoreCollectionList(@Field("token") String token, @Field("now_page") String now_page);

    //商品列表
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=goods&op=store_goods_list")
    Observable<StoreGoodsList> store_goods_list(@Field("store_id") String store_id,
                                                @Field("now_page") String now_page,
                                                @Field("token") String token, @Field("page_num") int page_num);

    //清空购物车
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_cart&op=cart_all_del")
    Observable<BaseBean> clearShopping(@Field("store_id") String store_id,
                                       @Field("token") String token);

    //购物车（更新购买数量）
    @FormUrlEncoded
//    @POST("mobile/index.php?act=member_cart&op=get_update_info_by_edit_quantity")
    @POST("mobileapp/index.php?act=member_cart&op=get_update_info_by_edit_quantity")
    Observable<ChangeNum> get_update_info_by_edit_quantity(@Field("cart_id") String cart_id,
                                                           @Field("quantity") String quantity,
                                                           @Field("token") String token);

    //商品列表页添加购物车
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=member_cart&op=update_cart_info_in_goods")
//    @POST("mobileapp/index.php?act=member_cart&op=cart_add")
    Observable<ChangeNum> update_cart_info_in_goods(@Field("goods_id") String goods_id,
                                                    @Field("quantity") String quantity,
                                                    @Field("token") String token);

    //显示店铺评价
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=evaluate_goods&op=evaluate_lists")
    Observable<CommentListBean> getStoreComment(@Field("store_id") int store_id,
                                                @Field("now_page") int now_page,
                                                @Field("now_sum") int now_sum);

    //收藏店铺列表
    @FormUrlEncoded
    @POST("mobile/index.php?act=store&op=store_info")
    Observable<StoreDetail> store_info(@Field("token") String token, @Field("store_id") String store_id);

    //店铺Banner
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=store&op=store_info")
    Observable<StoreDetailInfo> store_contactinfo(@Field("store_id") String store_id, @Field("token") String token);

    //店铺详情
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=store&op=store_contactinfo")
    Observable<MyStoreDetail> my_store_contactinfo(@Field("store_id") String store_id, @Field("token") String token);

    //购物车列表
    @FormUrlEncoded
//    @POST("mobile/index.php?act=member_cart&op=cart_list_old")
    @POST("mobileapp/index.php?act=member_cart&op=cart_list_old")
    Observable<Cart> cart_list_old(@Field("token") String token, @Field("store_id") String store_id);

    //商品详情
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=goods&op=goods_detail")
    Observable<GoodsDetailInfo> getGoodsDetail(@Field("token") String token, @Field("goods_id") String goods_id);

    //我的订单列表
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=apimember&op=member_order_list")
    Observable<MyOrder> myOrder(@Field("token") String token, @Field("status") int status,
                                @Field("page_now") int page_now, @Field("page_size") int page_size);

    //根据订单id改变订单状态（取消订单、确认收货、删除/恢复/永久删除订单）
    @FormUrlEncoded
    @POST("mobileapp/index.php?act=apimember&op=order_change_state")
    Observable<MyOrder> orderOperte(@Field("token") String token, @Field("order_id") String order_id,
                                    @Field("state_type") String state_type);

    /*//获取所有品牌
    @GET("ecmobile/?url=/home/brand")
    Observable<Brand> brand();

    //获取二级和三级商品分类
    @FormUrlEncoded
    @POST("ecmobile/?url=/home/category_child")
    Observable<DivideFirstS> category_child(@Field("parenr_id") String parenr_id);*/

}
