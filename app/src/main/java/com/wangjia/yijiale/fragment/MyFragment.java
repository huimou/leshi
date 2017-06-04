package com.wangjia.yijiale.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.activity.AboutActivity;
import com.wangjia.yijiale.activity.ChargeActivity;
import com.wangjia.yijiale.activity.LoginActivity;
import com.wangjia.yijiale.activity.MyCollectionActivity;
import com.wangjia.yijiale.activity.MyOrderActivity;
import com.wangjia.yijiale.activity.MySettingActivity;
import com.wangjia.yijiale.activity.QrCodeActivity;
import com.wangjia.yijiale.activity.StoreInActivity;
import com.wangjia.yijiale.activity.TxActivity;
import com.wangjia.yijiale.activity.UpdateZlActivity;
import com.wangjia.yijiale.address.AddressManageActivity;
import com.wangjia.yijiale.entity.ShowVipBean;
import com.wangjia.yijiale.entity.VipSubmitBean;
import com.wangjia.yijiale.iview.TxActivityView;
import com.wangjia.yijiale.presenter.impl.TxActivityPresenterImpl;
import com.wangjia.yijiale.pullzoom.PullToZoomScrollViewEx;
import com.wangjia.yijiale.utils.CircleImageView;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.SPUtils;
import com.wangjia.yijiale.utils.StringFunction;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin on 2016/10/26
 * 个人中心
 */
public class MyFragment extends Fragment implements TxActivityView {

    @Bind(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;
    @Bind(R.id.titleBar)
    LinearLayout titleBar;
    private Intent tIntent;
    private ImageView zoom;
    private View headView;
    private CircleImageView headViewView;
    private TextView user_name_tv, user_money_tv;
    private TxActivityPresenterImpl txActivityPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pull_to_zoom_scroll_view, container, false);
        tIntent = new Intent();
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }


    public void initData() {
        loadViewForCode();
        scrollView.setParallax(false);
        zoom = (ImageView) scrollView.getZoomView().findViewById(R.id.iv_zoom);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);

        txActivityPresenter = new TxActivityPresenterImpl(this, getActivity());
        txActivityPresenter.showVip();

    }

    public void initView() {
        scrollView.registerOnScrollViewAlph(new PullToZoomScrollViewEx.OnScrollAlphListener() {
            @Override
            public void setAlph(float alph) {
                titleBar.setAlpha(alph);
            }

            @Override
            public void setFAlph() {
                titleBar.setAlpha(1);
            }
        });

        final int store_id = (int) SPUtils.get(getActivity(), Constants.STORE_ID, 0);
        if (store_id != 0) {
            scrollView.getPullRootView().findViewById(R.id.my_pay_rl).setVisibility(View.VISIBLE);
            scrollView.getPullRootView().findViewById(R.id.my_pay_rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), QrCodeActivity.class);
                    intent.putExtra("store_id", store_id);
                    startActivity(intent);
                }
            });
        } else {
            scrollView.getPullRootView().findViewById(R.id.my_pay_rl).setVisibility(View.GONE);
        }

        scrollView.getPullRootView().findViewById(R.id.r_my_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.dfk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.dfh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.dsh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.ywc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.charge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), ChargeActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        scrollView.getPullRootView().findViewById(R.id.tx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), TxActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), MyCollectionActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), AddressManageActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        scrollView.getPullRootView().findViewById(R.id.store).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), StoreInActivity.class);
                startActivity(tIntent);
            }
        });
        scrollView.getPullRootView().findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIntent = new Intent(getActivity(), AboutActivity.class);
                startActivity(tIntent);
            }
        });

    }

    private void loadViewForCode() {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_head_view, null, false);
        headViewView = (CircleImageView) headView.findViewById(R.id.iv_user_head);
        user_name_tv = (TextView) headView.findViewById(R.id.user_name_tv);
        user_money_tv = (TextView) headView.findViewById(R.id.user_money_tv);

        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }

    @OnClick({R.id.setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                tIntent = new Intent(getActivity(), MySettingActivity.class);
                startActivity(tIntent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        headViewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringFunction.isNotNull(YiApplication.getInstance().getToken())) {
                    tIntent = new Intent(getActivity(), UpdateZlActivity.class);
                    startActivity(tIntent);
                } else {
                    tIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(tIntent);
                }
            }
        });
        if (StringFunction.isNotNull(SPUtils.get(getActivity(), Constants.TOKEN, "").toString())) {
            user_name_tv.setText(SPUtils.get(getActivity(), Constants.RealName, "").toString());
            String so = SPUtils.get(getActivity(), Constants.MEMBER_AVATAR, "").toString();
            if (StringFunction.isNotNull(so)) {
                Glide.with(getActivity()).load(so).into(headViewView);
            } else {
                Glide.with(getActivity()).load(R.mipmap.my).into(headViewView);
            }
        } else {
            user_name_tv.setText("未登录");
        }
    }

    @Override
    public void showVip(ShowVipBean getInfo) {
        if (StringFunction.isNotNull(getInfo.getDatas()) && StringFunction.isNotNull(getInfo.getDatas().getMember_info()) &&
                StringFunction.isNotNull(getInfo.getDatas().getMember_info().getAvailable_predeposit())) {
            if (StringFunction.isNotNull(SPUtils.get(getActivity(), Constants.TOKEN, "").toString())) {
                user_money_tv.setText("(账户余额:￥" + getInfo.getDatas().getMember_info().getAvailable_predeposit() + ")");
            }
        }
    }


    @Override
    public void vipTxApply(VipSubmitBean bean) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }
}
