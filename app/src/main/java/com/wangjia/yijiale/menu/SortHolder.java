package com.wangjia.yijiale.menu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.wangjia.yijiale.R;

import java.util.List;

/**
 *
 * 综合排序
 * Created by vonchenchen on 2016/4/5 0005.
 */
public class SortHolder extends BaseWidgetHolder<List<String>>{

    /** 默认排序 */
    public static final String SORT_BY_NORULE = "5";
    /** 离我最近 */
    public static final String SORT_BY_EVALUATION = "2";
    /** 好评率 */
    public static final String SORT_BY_PRICELOW = "3";
    /** 销量最高 */
    public static final String SORT_BY_PRICEHIGH = "1";
    /** 综合评分 */
    public static final String SORT_BY_DISTANCE = "4";

    /** 默认排序 */
    private View mComprehensiveView;
    /** 离我最近 */
    private View mHighEvaluateView;
    /** 销量最高 */
    private View mLowPriceView;
    /** 综合评分 */
    private View mHighPriceView;
    /** 好评率 */
    private View mDistanceView;

    private ImageView mRecordImageView;
    private ImageView mComprehensiveImage;
    private ImageView mHighEvaluateImage;
    private ImageView mLowPriceImage;
    private ImageView mHighPriceImage;
    private ImageView mDistanceImage;

    private OnSortInfoSelectedListener mOnSortInfoSelectedListener;

    public SortHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.layout_holder_sort, null);

        mComprehensiveView = view.findViewById(R.id.re_sort1);
        mHighEvaluateView = view.findViewById(R.id.re_sort2);
        mLowPriceView = view.findViewById(R.id.re_sort4);
        mHighPriceView = view.findViewById(R.id.re_sort5);
        mDistanceView = view.findViewById(R.id.re_sort6);

        mComprehensiveImage = (ImageView) view.findViewById(R.id.img_sort1);
        mHighEvaluateImage = (ImageView) view.findViewById(R.id.img_sort2);
        mLowPriceImage = (ImageView) view.findViewById(R.id.img_sort4);
        mHighPriceImage = (ImageView) view.findViewById(R.id.img_sort5);
        mDistanceImage = (ImageView) view.findViewById(R.id.img_sort6);

        //默认排序
        mComprehensiveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retSortInfo(SORT_BY_NORULE, mComprehensiveImage);
            }
        });

        //离我最近
        mHighEvaluateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retSortInfo(SORT_BY_EVALUATION, mHighEvaluateImage);
            }
        });

        //销量最高
        mLowPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retSortInfo(SORT_BY_PRICEHIGH, mLowPriceImage);
            }
        });

        //综合评分
        mHighPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retSortInfo(SORT_BY_DISTANCE, mHighPriceImage);
            }
        });
        //好评率
        mDistanceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retSortInfo(SORT_BY_PRICELOW, mDistanceImage);
            }
        });

        return view;
    }

    @Override
    public void refreshView(List<String> data) {
        mComprehensiveImage.setVisibility(View.INVISIBLE);
        mHighEvaluateImage.setVisibility(View.INVISIBLE);
        mLowPriceImage.setVisibility(View.INVISIBLE);
        mHighPriceImage.setVisibility(View.INVISIBLE);
        mDistanceImage.setVisibility(View.INVISIBLE);
    }

    private void retSortInfo(String info, ImageView imageView){

        if(mRecordImageView != null){
            mRecordImageView.setVisibility(View.INVISIBLE);
        }
        mRecordImageView = imageView;

        imageView.setVisibility(View.VISIBLE);

        if(mOnSortInfoSelectedListener != null){
            mOnSortInfoSelectedListener.onSortInfoSelected(info);
        }
    }

    public void setOnSortInfoSelectedListener(OnSortInfoSelectedListener onSortInfoSelectedListener){
        this.mOnSortInfoSelectedListener = onSortInfoSelectedListener;
    }

    public interface OnSortInfoSelectedListener{
        void onSortInfoSelected(String info);
    }
}
