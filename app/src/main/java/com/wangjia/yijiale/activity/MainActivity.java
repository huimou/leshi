package com.wangjia.yijiale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.adapter.FragmentTabAdapter;
import com.wangjia.yijiale.fragment.HomeFragment;
import com.wangjia.yijiale.fragment.MyFragment;
import com.wangjia.yijiale.fragment.NearFragment;
import com.wangjia.yijiale.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主界面
 */
public class MainActivity extends FragmentActivity {

    @Bind(R.id.main_content)
    FrameLayout mainContent;

    @Bind(R.id.main_bottom_tabs)
    RadioGroup mainBottomTabs;

    /**
     * Called when the activity is first created.
     */
    public List<Fragment> fragments;
    FragmentTabAdapter tabAdapter;
    @Bind(R.id.main_home)
    RadioButton mainHome;
    @Bind(R.id.main_near)
    RadioButton mainNear;
    @Bind(R.id.main_my)
    RadioButton mainMy;
    private Intent i;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    fragments = new ArrayList<Fragment>();
                    fragments.add(new HomeFragment());
                    fragments.add(new NearFragment());
                    fragments.add(new MyFragment());
                    tabAdapter = new FragmentTabAdapter(MainActivity.this, fragments, R.id.main_content, mainBottomTabs, 0);
                    tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
                        @Override
                        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                            L.e("Extra---- " + index + " checked!!! ");
                        }
                    });
                    break;
            }
        }
    };
    private AMapLocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        YiApplication.addActivity(MainActivity.this);
        mHandler.sendEmptyMessage(200);
        initLocation();
        startLocation();
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
                double latitude = loc.getLatitude();
                double longitude = loc.getLongitude();
                YiApplication.setLatitude(String.valueOf(latitude));
                YiApplication.setLongitude(String.valueOf(longitude));
                L.e("维度：" + latitude + "经度：" + longitude);
//                String result = LocationUtils.getLocationStr(loc);
//                location_tv.setText(loc.getCity());
                stopLocation();
            } else {
//                location_tv.setText("定位失败，loc is null");
                stopLocation();
            }
        }
    };


    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showLogoutDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    /**
     * 注销登录
     */
    /*private void showLogoutDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_showmsg);
        TextView tv_message = (TextView) window.findViewById(R.id.message);
        tv_message.setText("你是否要退出应用?");
        Button cancel = (Button) window.findViewById(R.id.btn_left);
        Button ok = (Button) window.findViewById(R.id.btn_right);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注销登录操作
                L.e("注销~~");
                MyApplication.getInstance().exitActivity();
                dialog.dismiss();
            }
        });
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
