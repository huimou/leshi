package com.wangjia.yijiale.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjia.yijiale.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索菜单栏
 * Created by vonchenchen on 2016/4/5 0005.
 */
public class SelectMenuView extends LinearLayout {

    //三种类别的选项
    private static final int TAB_SUBJECT = 1;
    private static final int TAB_SORT = 2;
    private static final int TAB_SELECT = 3;

    private Context mContext;

    //三种类别的视图
    private View mSubjectView;
    private View mSortView;
    private View mSelectView;
    //根视图
    private View mRootView;
    //popupWindow
    private View mPopupWindowView;
    //主布局
    private RelativeLayout mMainContentLayout;
    private View mBackView;

    /**
     * type1
     */
    private SubjectHolder mSubjectHolder;
    /**
     * type2
     */
    private SortHolder mSortHolder;
    /**
     * type3
     */
    private SubjectHolder mSelectHolder;

    /**
     * 与外部通信传递数据的接口
     */
    //用于更新数据
    private OnMenuSelectDataChangedListener mOnMenuSelectDataChangedListener;

    private RelativeLayout mContentLayout;

    //科目显示的文字和图片
    private TextView mSubjectText;
    private ImageView mSubjectArrowImage;
    private TextView mSortText;
    private ImageView mSortArrowImage;
    private TextView mSelectText;
    private ImageView mSelectArrowImage;

    //存储文字的List
    private List<String> mGroupList;
    private List<String> mPrimaryList;
    private List<String> mJuniorList;
    //    private List<String> mHighList;
    private List<List<String>> mSubjectDataList;

    private int mTabRecorder = -1;
    private ArrayList<List<String>> mSubjectStroeAddressList;

    //构造方法
    public SelectMenuView(Context context) {
        super(context);
        this.mContext = context;
        this.mRootView = this;
//        init();
    }

    public SelectMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mRootView = this;
//        init();
    }

    /*private void init() {

        //初始化数据
        mGroupList = new ArrayList<String>();
        mGroupList.add("美食");
        mGroupList.add("酒店住宿");
//        mGroupList.add("休闲娱乐");
        mPrimaryList = new ArrayList<String>();
        mPrimaryList.add("全部");
        mPrimaryList.add("火锅");
        mPrimaryList.add("甜点");
        mJuniorList = new ArrayList<String>();
        mJuniorList.add("全部");
        mJuniorList.add("瑜伽酒店");
        mJuniorList.add("58酒店");
        mJuniorList.add("7天连锁酒店");
//        mHighList = new ArrayList<String>();
//        mHighList.add("全部");
//        mHighList.add("沐浴");
//        mHighList.add("洗脚");
//        mHighList.add("KTV");

        mSubjectDataList = new ArrayList<List<String>>();
        mSubjectDataList.add(mGroupList);
        mSubjectDataList.add(mPrimaryList);
        mSubjectDataList.add(mJuniorList);
//        mSubjectDataList.add(mHighList);

        //type1
        mSubjectHolder = new SubjectHolder(mContext);
        mSubjectHolder.refreshData(mSubjectDataList, 0, -1);
        mSubjectHolder.setOnRightListViewItemSelectedListener(new SubjectHolder.OnRightListViewItemSelectedListener() {
            @Override
            public void OnRightListViewItemSelected(int leftIndex, int rightIndex, String text) {

                if (mOnMenuSelectDataChangedListener != null) {
                    int grade = leftIndex-1;
                    int subject = getSubjectId(rightIndex);
                    mOnMenuSelectDataChangedListener.onSubjectChanged(grade + "", subject + "");
                }
                dismissPopupWindow();
//                Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_SHORT).show();
                mSubjectText.setText(text);
            }
        });

        //type2
        mSortHolder = new SortHolder(mContext);
        mSortHolder.setOnSortInfoSelectedListener(new SortHolder.OnSortInfoSelectedListener() {
            @Override
            public void onSortInfoSelected(String info) {

                if (mOnMenuSelectDataChangedListener != null) {
                    mOnMenuSelectDataChangedListener.onSortChanged(info);
                }

                dismissPopupWindow();
                mSortText.setText(getSortString(info));
                //Toast.makeText(UIUtils.getContext(), info, Toast.LENGTH_SHORT).show();
            }
        });

        //type3
        mSelectHolder = new SubjectHolder(mContext);
        mSubjectHolder.setOnRightListViewItemSelectedListener(new SubjectHolder.OnRightListViewItemSelectedListener() {
            @Override
            public void OnRightListViewItemSelected(int leftIndex, int rightIndex, String text) {

                if (mOnMenuSelectDataChangedListener != null) {
                    int grade = leftIndex-1;
                    int subject = getSubjectId(rightIndex);
                    mOnMenuSelectDataChangedListener.onSubjectChanged(grade + "", subject + "");
                }

                dismissPopupWindow();
                //Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_SHORT).show();
                mSubjectText.setText(text);
            }
        });
    }*/


    public void setInitData(ArrayList<List<String>> mSubjectDataList, ArrayList<List<String>> mSubjectStroeAddressList) {
        //type1
        this.mSubjectDataList = mSubjectDataList;
        mSubjectHolder = new SubjectHolder(mContext);
        mSubjectHolder.refreshData(mSubjectDataList, 0, -1);
        mSubjectHolder.setOnRightListViewItemSelectedListener(new SubjectHolder.OnRightListViewItemSelectedListener() {
            @Override
            public void OnRightListViewItemSelected(int leftIndex, int rightIndex, String text) {

                if (mOnMenuSelectDataChangedListener != null) {
                    int grade = leftIndex;
                    int subject = getSubjectId(rightIndex);
                    mOnMenuSelectDataChangedListener.onSubjectChanged(grade + "", subject + "");
                }
                dismissPopupWindow();
                mSubjectText.setText(text);
            }
        });

        //type2
        mSortHolder = new SortHolder(mContext);
        mSortHolder.setOnSortInfoSelectedListener(new SortHolder.OnSortInfoSelectedListener() {
            @Override
            public void onSortInfoSelected(String info) {

                if (mOnMenuSelectDataChangedListener != null) {
                    mOnMenuSelectDataChangedListener.onSortChanged(info);
                }

                dismissPopupWindow();
                mSortText.setText(getSortString(info));
                //Toast.makeText(UIUtils.getContext(), info, Toast.LENGTH_SHORT).show();
            }
        });

        //type3
        if (mSubjectStroeAddressList != null) {
            this.mSubjectStroeAddressList = mSubjectStroeAddressList;
            mSelectHolder = new SubjectHolder(mContext);
            mSelectHolder.refreshData(mSubjectStroeAddressList, 0, -1);
            mSelectHolder.setOnRightListViewItemSelectedListener(new SubjectHolder.OnRightListViewItemSelectedListener() {
                @Override
                public void OnRightListViewItemSelected(int leftIndex, int rightIndex, String text) {

                    if (mOnMenuSelectDataChangedListener != null) {
                        int grade = leftIndex;
                        int subject = getSubjectId(rightIndex);
                        mOnMenuSelectDataChangedListener.onSelectedChanged(grade + "", subject + "");
                    }

                    dismissPopupWindow();
                    mSelectText.setText(text);
                }
            });
        }

    }

    public void setHanddinAddress() {
        mSelectView.setVisibility(GONE);
    }


    private int getSubjectId(int index) {
        return index;
    }

    //    onFinishInflate 当View中所有的子控件均被映射成xml后触发
//    当加载完成xml后，就会执行这个方法。
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(mContext, R.layout.layout_search_menu, this);

        mSubjectText = (TextView) findViewById(R.id.subject);
        mSubjectArrowImage = (ImageView) findViewById(R.id.img_sub);

        mSelectText = (TextView) findViewById(R.id.tv_select);
        mSelectArrowImage = (ImageView) findViewById(R.id.img_sc);

        mSortText = (TextView) findViewById(R.id.comprehensive_sorting);
        mSortArrowImage = (ImageView) findViewById(R.id.img_cs);

        mContentLayout = (RelativeLayout) findViewById(R.id.rl_content);

        mPopupWindowView = View.inflate(mContext, R.layout.layout_search_menu_content, null);
        mMainContentLayout = (RelativeLayout) mPopupWindowView.findViewById(R.id.rl_main);
        //mBackView = mPopupWindowView.findViewById(R.id.ll_background);

        mSubjectView = findViewById(R.id.ll_subject);
        mSelectView = findViewById(R.id.ll_select);
        mSortView = findViewById(R.id.ll_sort);

        //点击 type1 弹出菜单
        mSubjectView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMenuSelectDataChangedListener != null) {
                    mOnMenuSelectDataChangedListener.onViewClicked(mSubjectView);
                }
                handleClickSubjectView();
            }
        });
        //点击 type2 弹出菜单
        mSortView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMenuSelectDataChangedListener != null) {
                    mOnMenuSelectDataChangedListener.onViewClicked(mSortView);
                }
                handleClickSortView();
            }
        });
        //点击 type3 弹出菜单
        mSelectView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMenuSelectDataChangedListener != null) {
                    mOnMenuSelectDataChangedListener.onViewClicked(mSelectView);
                }
                handleClickSelectView();
            }
        });

        //点击黑色半透明部分，菜单收回
        mContentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupWindow();
            }
        });
    }

    private void handleClickSubjectView() {
        try {
            //清空内容View中的View
            mMainContentLayout.removeAllViews();
            //将我们已经创建好的ViewHolder拿出，取出其中的View贴到内容View中
            mMainContentLayout.addView(mSubjectHolder.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //处理弹窗动作
            popUpWindow(TAB_SUBJECT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClickSortView() {

        mMainContentLayout.removeAllViews();
        mMainContentLayout.addView(mSortHolder.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popUpWindow(TAB_SORT);
    }

    private void handleClickSelectView() {

        mMainContentLayout.removeAllViews();
        mMainContentLayout.addView(mSelectHolder.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popUpWindow(TAB_SELECT);
    }

    private void popUpWindow(int tab) {
        if (mTabRecorder != -1) {
            resetTabExtend(mTabRecorder);
        }
        extendsContent();
        setTabExtend(tab);
        mTabRecorder = tab;
    }

    private void extendsContent() {
        mContentLayout.removeAllViews();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentLayout.addView(mPopupWindowView, params);
    }

    private void dismissPopupWindow() {
        mContentLayout.removeAllViews();
        setTabClose();
    }

    public void setOnMenuSelectDataChangedListener(OnMenuSelectDataChangedListener onMenuSelectDataChangedListener) {
        this.mOnMenuSelectDataChangedListener = onMenuSelectDataChangedListener;
    }

    public interface OnMenuSelectDataChangedListener {

        void onSubjectChanged(String grade, String subjects);

        void onSortChanged(String sortType);

        void onSelectedChanged(String gender, String classType);

        void onViewClicked(View view);

        //筛选菜单，当点击其他处菜单收回后，需要更新当前选中项
        void onSelectedDismissed(String gender, String classType);
    }

    private void setTabExtend(int tab) {
        if (tab == TAB_SUBJECT) {
            mSubjectText.setTextColor(getResources().getColor(R.color.blue));
            mSubjectArrowImage.setImageResource(R.mipmap.ic_up_blue);
        } else if (tab == TAB_SORT) {
            mSortText.setTextColor(getResources().getColor(R.color.blue));
            mSortArrowImage.setImageResource(R.mipmap.ic_up_blue);
        } else if (tab == TAB_SELECT) {
            mSelectText.setTextColor(getResources().getColor(R.color.blue));
            mSelectArrowImage.setImageResource(R.mipmap.ic_up_blue);
        }
    }

    private void resetTabExtend(int tab) {
        if (tab == TAB_SUBJECT) {
            mSubjectText.setTextColor(getResources().getColor(R.color.gray));
            mSubjectArrowImage.setImageResource(R.mipmap.ic_down);
        } else if (tab == TAB_SORT) {
            mSortText.setTextColor(getResources().getColor(R.color.gray));
            mSortArrowImage.setImageResource(R.mipmap.ic_down);
        } else if (tab == TAB_SELECT) {
            mSelectText.setTextColor(getResources().getColor(R.color.gray));
            mSelectArrowImage.setImageResource(R.mipmap.ic_down);
        }
    }

    private void setTabClose() {

        mSubjectText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSubjectArrowImage.setImageResource(R.mipmap.ic_down);

        mSortText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSortArrowImage.setImageResource(R.mipmap.ic_down);

        mSelectText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSelectArrowImage.setImageResource(R.mipmap.ic_down);
    }

    @NonNull
    private String getSortString(String info) {
        if (SortHolder.SORT_BY_NORULE.equals(info)) {
            return "默认排序";
        } else if (SortHolder.SORT_BY_EVALUATION.equals(info)) {
            return "离我最近";
        } else if (SortHolder.SORT_BY_PRICELOW.equals(info)) {
            return "好评率";
        } else if (SortHolder.SORT_BY_PRICEHIGH.equals(info)) {
            return "销量最高";
        } else if (SortHolder.SORT_BY_DISTANCE.equals(info)) {
            return "综合评分";
        }
        return "sort1";
    }

    public void clearAllInfo() {
        //清除控件内部选项
        mSubjectHolder.refreshData(mSubjectDataList, 0, -1);
        mSortHolder.refreshView(null);
        mSelectHolder.refreshView(null);

        //清除菜单栏显示
        mSubjectText.setText("type1");

        mSortText.setText("type2");
    }
}