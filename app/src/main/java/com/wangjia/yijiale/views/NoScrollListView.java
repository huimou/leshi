package com.wangjia.yijiale.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.wangjia.yijiale.utils.L;

/**
 * Created by samson on 2016/3/24.
 * 不滚动listView
 */
public class NoScrollListView extends ListView {
    public boolean isOnMeasure;
    public NoScrollListView(Context context) {
        super(context);
    }
    public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        L.e("NoScrollListView : onMeasure");
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        isOnMeasure = true;
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        L.e("NoScrollListView : onLayout");
        isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }

    public boolean isOnMeasure() {
        return isOnMeasure;
    }
}