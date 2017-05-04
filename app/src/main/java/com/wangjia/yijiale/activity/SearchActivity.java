package com.wangjia.yijiale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wangjia.yijiale.R;
import com.wangjia.yijiale.adapter.SearchStoreViewAdapter;
import com.wangjia.yijiale.entity.SearchShopBean;
import com.wangjia.yijiale.iview.SearchActivityView;
import com.wangjia.yijiale.presenter.SearchActivityPresenter;
import com.wangjia.yijiale.presenter.impl.SearchActivityPresenterImpl;
import com.wangjia.yijiale.recycle.PullLoadMoreRecyclerView;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchActivityView {

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.search_pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView searchPullLoadMoreRecyclerView;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.search_ll)
    LinearLayout searchLl;
    @Bind(R.id.top_search)
    LinearLayout topSearch;
    private SearchActivityPresenter searchActivityPresenter;
    private Intent i;
    private SearchStoreViewAdapter searchStoreViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_search);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        searchActivityPresenter = new SearchActivityPresenterImpl(this, SearchActivity.this);
        searchActivityPresenter.getData(1, etSearch.getText().toString(), "", "");
    }

    public void initView() {
        new Titlebulder(SearchActivity.this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("搜索")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    public void getData(SearchShopBean model) {
        if (model.getCode() == Constants.RESPONSE_SUCCESS) {
            searchStoreViewAdapter = new SearchStoreViewAdapter(this, model.getDatas().getStore_list());
            searchPullLoadMoreRecyclerView.setAdapter(searchStoreViewAdapter);
            searchPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            setOrientation2(model);
        }
        L.TShort(SearchActivity.this, model.getMsg());
    }


    /**
     * 设置RecycleView布局方式
     */
    public void setOrientation2(final SearchShopBean model) {
        searchPullLoadMoreRecyclerView.setLinearLayout();
        try {
            searchStoreViewAdapter.setmOnItemClickListener(new SearchStoreViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                    intent.putExtra("store_id", model.getDatas().getStore_list().get(postion).getStore_id());
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int postion) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(SearchActivity.this, "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }

    @OnClick({ R.id.search_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_ll:
                searchActivityPresenter.getData(1, etSearch.getText().toString(), "", "");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
