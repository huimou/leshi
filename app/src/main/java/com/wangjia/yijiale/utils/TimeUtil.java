package com.wangjia.yijiale.utils;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title 计时器
 * @Description:
 * @Date 2016/5/13 0013 上午 10:12
 */
public class TimeUtil extends CountDownTimer {
    private Activity mActivity;
    private TextView mTextview;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeUtil(Activity context, long millisInFuture, long countDownInterval, TextView mTextview) {
        super(millisInFuture, countDownInterval);
        this.mTextview = mTextview;
        this.mActivity = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (null!= mActivity && !mActivity.isFinishing()) {
            mTextview.setEnabled(false);
            mTextview.setText((millisUntilFinished / 1000) + "(s)");
        }
    }

    @Override
    public void onFinish() {
        if (null!= mActivity && !mActivity.isFinishing()) {
            mTextview.setText("重新获取验证码");
            mTextview.setEnabled(true);
        }
    }

}
