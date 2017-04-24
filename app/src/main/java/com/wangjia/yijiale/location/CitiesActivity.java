/**
 * @author Leestar54
 * http://www.cnblogs.com/leestar54
 */
package com.wangjia.yijiale.location;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.entity.AreaList;
import com.wangjia.yijiale.event.HomeFragmentEvent;
import com.wangjia.yijiale.iview.AddAddressChooseActivityView;
import com.wangjia.yijiale.presenter.impl.AddAddressChooseActivityPresenterImpl;
import com.wangjia.yijiale.utils.RxBus;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 选择城市界面
 */
public class CitiesActivity extends AppCompatActivity implements AddAddressChooseActivityView {
    LinearLayout layoutIndex;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    AddAddressChooseActivityPresenterImpl citiesActivityPresenter;
    ArrayList<CityListItem> list = new ArrayList<CityListItem>();
    /**
    /**
     * 字母索引表
     */
    private String[] str_index = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};// "#",

    private int height;// 字体高度
    private List<CityListItem> listData;
    private ListView listView;
    private CityListAdapter adapter;
    private TextView tv_show;// 中间显示标题的文本
    private TextView location_tv;
    private AMapLocationClient locationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city_list);
        initView();
        initData();

    }

    public void initData() {
//        getData();
        tv_show = (TextView) findViewById(R.id.tv);
        tv_show.setBackgroundColor(Color.parseColor("#66000000"));
        tv_show.setVisibility(View.INVISIBLE);
        citiesActivityPresenter = new AddAddressChooseActivityPresenterImpl(this, this);
        citiesActivityPresenter.getShengList("0");
    }

    @Override
    public void getShengList(AreaList model) {
        List<AreaList.DatasBean.AreaListBean> area_list = model.getDatas().getArea_list();
        if (area_list != null) {
            for (AreaList.DatasBean.AreaListBean areaListBean : area_list) {
                CityListItem cityListItem = new CityListItem();
                cityListItem.setName(areaListBean.getArea_name());
                cityListItem.setId(Integer.parseInt(areaListBean.getArea_id()));
                list.add(cityListItem);
            }

            // 获取首字母
            for (CityListItem cityListItem : list) {
                cityListItem.setIndex(String.valueOf(ChineseUtils.getHanyuPinyin(
                        cityListItem.getName()).charAt(0)));
            }
            // 排序
            LetterComparator lc = new LetterComparator();
            Collections.sort(list, lc);


            adapter = new CityListAdapter(CitiesActivity.this, list, str_index);
            listView.setAdapter(adapter);
        }

    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("定位")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        layoutIndex = (LinearLayout) this.findViewById(R.id.layout);
        layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));
        View head_view = View.inflate(this, R.layout.activity_city_list_head, null);
        listView = (ListView) findViewById(R.id.listView1);
        location_tv = (TextView) head_view.findViewById(R.id.location_tv);

        listView.addHeaderView(head_view);
        initLocation();
        startLocation();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = "";
                int city_id = 0;
                if (position == 0) {
                    name = location_tv.getText().toString();
                } else {
                    name = list.get(position - 1).getName();
                    city_id = list.get(position - 1).getId();
                }
                RxBus.getDefault().send(new HomeFragmentEvent(name, city_id));
                finish();
            }
        });

    }


    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
//        resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
//                String result = LocationUtils.getLocationStr(loc);
                location_tv.setText(loc.getCity());
                stopLocation();
            } else {
                location_tv.setText("定位失败，loc is null");
                stopLocation();
            }
        }
    };

    /**
     * 获取城市列表
     */
   /* public void getData() {
        CityListItem ci1 = new CityListItem();
        ci1.setName("北京");
        CityListItem ci2 = new CityListItem();
        ci2.setName("上海");
        CityListItem ci3 = new CityListItem();
        ci3.setName("广州");
        CityListItem ci4 = new CityListItem();
        ci4.setName("广西");
        CityListItem ci5 = new CityListItem();
        ci5.setName("长沙");
        CityListItem ci6 = new CityListItem();
        ci6.setName("贵阳");
        CityListItem ci7 = new CityListItem();
        ci7.setName("福建");

        ArrayList<CityListItem> list = new ArrayList<CityListItem>();
        list.add(ci1);
        list.add(ci1);
        list.add(ci1);
        list.add(ci1);
        list.add(ci1);
        list.add(ci1);
        list.add(ci2);
        list.add(ci2);
        list.add(ci2);
        list.add(ci2);
        list.add(ci2);
        list.add(ci2);
        list.add(ci3);
        list.add(ci3);
        list.add(ci3);
        list.add(ci3);
        list.add(ci3);
        list.add(ci4);
        list.add(ci4);
        list.add(ci4);
        list.add(ci4);
        list.add(ci4);
        list.add(ci5);
        list.add(ci5);
        list.add(ci5);
        list.add(ci5);
        list.add(ci6);
        list.add(ci6);
        list.add(ci6);
        list.add(ci6);
        list.add(ci7);
        list.add(ci7);
        list.add(ci7);
        list.add(ci7);

        // 获取首字母
        for (CityListItem cityListItem : list) {
            cityListItem.setIndex(String.valueOf(ChineseUtils.getHanyuPinyin(
                    cityListItem.getName()).charAt(0)));
        }
        // 排序
        LetterComparator lc = new LetterComparator();
        Collections.sort(list, lc);


        adapter = new CityListAdapter(CitiesActivity.this, list, str_index);
        listView.setAdapter(adapter);

    }*/
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // 在oncreate里面执行下面的代码没反应，因为oncreate里面得到的getHeight=0
        // System.out.println("layoutIndex.getHeight()=" +
        // layoutIndex.getHeight());
        height = layoutIndex.getHeight() / str_index.length;
        getIndexView();
    }

    /**
     * 绘制索引列表
     */
    public void getIndexView() {
        LinearLayout.LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, height);
        // params.setMargins(10, 5, 10, 0);
        for (int i = 0; i < str_index.length; i++) {
            final TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText(str_index[i]);
            tv.setPadding(10, 0, 10, 0);
            layoutIndex.addView(tv);
            layoutIndex.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event)

                {
                    float y = event.getY();
                    int index = (int) (y / height);
                    if (index > -1 && index < str_index.length) {// 防止越界
                        String key = str_index[index];
                        if (adapter.getSelector().containsKey(key)) {
                            // 获得位置
                            int pos = adapter.getSelector().get(key);
                            if (listView.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
                                listView.setSelectionFromTop(
                                        pos + listView.getHeaderViewsCount(), 0);
                            } else {
                                listView.setSelectionFromTop(pos, 0);// 滑动到第一项
                            }
                            tv_show.setVisibility(View.VISIBLE);
                            tv_show.setText(str_index[index]);
                        }
                    }
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // 按下颜色
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#aa000000"));
                            break;

                        case MotionEvent.ACTION_MOVE:

                            break;
                        case MotionEvent.ACTION_UP:
                            // 释放还原
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#00ffffff"));
                            tv_show.setVisibility(View.INVISIBLE);
                            break;
                    }
                    return true;
                }
            });
        }
    }


    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(CitiesActivity.this, "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }


    @Override
    public void getShiList(AreaList model) {

    }

    @Override
    public void getQuList(AreaList model) {

    }

    private class LetterComparator implements Comparator<CityListItem> {

        @Override
        public int compare(CityListItem lhs, CityListItem rhs) {
            return Collator.getInstance().compare(lhs.getIndex(),
                    rhs.getIndex());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }
}
