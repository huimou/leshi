package com.wangjia.yijiale.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

import com.wangjia.yijiale.utils.L;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/8/25.
 */
/* 屏蔽 滑动事件
        * Created by fc on 2015/7/16.
        */
public class RecycleScrollview extends ScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;
    private OnScrollAlphListener _listener;
    private int _calCount;

    public interface OnScrollBottomListener {
        void srollToBottom();
    }

    public interface OnScrollAlphListener {
        void setAlph(float alph);
        void setFAlph();
    }

    public void registerOnScrollViewAlph(OnScrollAlphListener l) {
        _listener = l;
    }

    public void registerOnScrollViewScrollToBottom(OnScrollBottomListener l) {
        //_listener = l;
    }

    public void unRegisterOnScrollViewScrollToBottom() {
        _listener = null;
    }

    public RecycleScrollview(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public RecycleScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public RecycleScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = this.getChildAt(0);
        DecimalFormat df = new DecimalFormat("0.00");
        if(t <= 600) {
            _listener.setAlph(Float.parseFloat(df.format((float)Math.abs(t)/(float) 600)));
        }else {
            _listener.setFAlph();
        }
        if (this.getHeight() + this.getScrollY() == view.getHeight()) {
            _calCount++;
            if (_calCount == 1) {
                if (_listener != null) {
                    //_listener.srollToBottom();
                }
            }
        } else {
            _calCount = 0;
        }
    }
}
