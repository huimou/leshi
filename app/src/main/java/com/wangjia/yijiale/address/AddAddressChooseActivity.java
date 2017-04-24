package com.wangjia.yijiale.address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.activity.MainActivity;
import com.wangjia.yijiale.entity.AddAddress;
import com.wangjia.yijiale.entity.AreaList;
import com.wangjia.yijiale.iview.AddAddressChooseActivityView;
import com.wangjia.yijiale.presenter.AddAddressChooseActivityPresenter;
import com.wangjia.yijiale.presenter.impl.AddAddressChooseActivityPresenterImpl;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加地址(地址选择页面)
 */
public class AddAddressChooseActivity extends AppCompatActivity implements AddAddressChooseActivityView{
    @Bind(R.id.my_set_adresschoose_listview_1)
    ListView mySetAdresschooseListview1;
    @Bind(R.id.my_set_adresschoose_1)
    LinearLayout mySetAdresschoose1;
    @Bind(R.id.my_set_adresschoose_listview_2)
    ListView mySetAdresschooseListview2;
    @Bind(R.id.my_set_adresschoose_2)
    LinearLayout mySetAdresschoose2;
    @Bind(R.id.my_set_adresschoose_listview_3)
    ListView mySetAdresschooseListview3;
    @Bind(R.id.my_set_adresschoose_3)
    LinearLayout mySetAdresschoose3;
    @Bind(R.id.my_set_adresschoose_sheng_2)
    TextView mySetAdresschooseSheng2;
    @Bind(R.id.my_set_adresschoose_sheng_3)
    TextView mySetAdresschooseSheng3;
    @Bind(R.id.my_set_adresschoose_shi_3)
    TextView mySetAdresschooseShi3;
    private List<String> sheng = new ArrayList<String>();
    private List<String> shengIds = new ArrayList<String>();
    private List<String> shi = new ArrayList<String>();
    private List<String> shiIds = new ArrayList<String>();
    private List<String> qu = new ArrayList<String>();
    private List<String> quIds = new ArrayList<String>();
    private ArrayAdapter<String> shengAdapter;
    private ArrayAdapter<String> shiAdapter;
    private ArrayAdapter<String> quAdapter;
    private static String shengStr, shiStr, quStr;
    private static String shengId, shiId, quId;
    private AreaList shengObject;
    private AreaList shiObject;
    private AreaList quObject;
    private AddAddressChooseActivityPresenter addAddressChooseActivityPresenter;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    for (int i = 0; i < shengObject.getDatas().getArea_list().size(); i++) {
                        sheng.add(shengObject.getDatas().getArea_list().get(i).getArea_name());
                        shengIds.add(shengObject.getDatas().getArea_list().get(i).getArea_id());
                    }
                    shengAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    for (int i = 0; i < shiObject.getDatas().getArea_list().size(); i++) {
                        shi.add(shiObject.getDatas().getArea_list().get(i).getArea_name());
                        shiIds.add(shiObject.getDatas().getArea_list().get(i).getArea_id());
                    }
                    shiAdapter.notifyDataSetChanged();
                    quAdapter.clear();
                    quAdapter.notifyDataSetChanged();
                    break;
                case 3:
                    for (int i = 0; i < quObject.getDatas().getArea_list().size(); i++) {
                        qu.add(quObject.getDatas().getArea_list().get(i).getArea_name());
                        quIds.add(quObject.getDatas().getArea_list().get(i).getArea_id());
                    }
                    quAdapter.notifyDataSetChanged();
                    if (qu.size() < 1) {
                        mySetAdresschoose1.setVisibility(View.GONE);
                        mySetAdresschoose2.setVisibility(View.VISIBLE);
                        mySetAdresschoose3.setVisibility(View.GONE);
                        Intent i = new Intent(AddAddressChooseActivity.this, AddBuyAddressActivity.class);
                        i.putExtra("address", shengStr + "," + shiStr);
                        i.putExtra("shengID", shengId);
                        i.putExtra("shiID", shiId);
                        setResult(RESULT_OK,i);
                        finish();
                    } else {
                        mySetAdresschoose1.setVisibility(View.GONE);
                        mySetAdresschoose2.setVisibility(View.GONE);
                        mySetAdresschoose3.setVisibility(View.VISIBLE);
                        mySetAdresschooseListview1.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        new Titlebulder(this).setTitleName("地址选择")
                .setLeftImage(R.mipmap.arrowleft)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        initView();
        initData();
    }

    public void initView() {
        addAddressChooseActivityPresenter = new AddAddressChooseActivityPresenterImpl(this, AddAddressChooseActivity.this);
        mySetAdresschoose1.setVisibility(View.VISIBLE);
        mySetAdresschoose2.setVisibility(View.GONE);
        mySetAdresschoose3.setVisibility(View.GONE);
        mySetAdresschooseSheng2 = (TextView) this.findViewById(R.id.my_set_adresschoose_sheng_2);
        mySetAdresschooseSheng3 = (TextView) this.findViewById(R.id.my_set_adresschoose_sheng_3);
        mySetAdresschooseShi3 = (TextView) this.findViewById(R.id.my_set_adresschoose_shi_3);
        shengAdapter = new ArrayAdapter(this, R.layout.add_addresschoose_listview_item, R.id.my_set_adresschoose_textview, sheng);
        shiAdapter = new ArrayAdapter(this, R.layout.add_addresschoose_listview_item, R.id.my_set_adresschoose_textview, shi);
        quAdapter = new ArrayAdapter(this, R.layout.add_addresschoose_listview_item, R.id.my_set_adresschoose_textview, qu);
        mySetAdresschooseListview1.setAdapter(shengAdapter);
        mySetAdresschooseListview2.setAdapter(shiAdapter);
        mySetAdresschooseListview3.setAdapter(quAdapter);
        addAddressChooseActivityPresenter.getShengList("0");
    }


    public void initData() {

        mySetAdresschooseListview1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                mySetAdresschoose1.setVisibility(View.GONE);
                mySetAdresschoose2.setVisibility(View.VISIBLE);
                mySetAdresschoose3.setVisibility(View.GONE);
                shi.clear();
                String name = sheng.get(position);
                shengId = shengIds.get(position);
                shengStr = name;
                mySetAdresschooseSheng2.setText(name);
                addAddressChooseActivityPresenter.getShiList(shengObject.getDatas().getArea_list().get(position).getArea_id());
            }
        });

        mySetAdresschooseListview2.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                qu.clear();
                String name = shi.get(position);
                shiId = shiIds.get(position);
                shiStr = name;
                mySetAdresschooseSheng3.setText(shengStr);
                mySetAdresschooseShi3.setText(name);
                addAddressChooseActivityPresenter.getQuList(shengObject.getDatas().getArea_list().get(position).getArea_id());
            }
        });

        mySetAdresschooseListview3.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                String name = qu.get(position);
                quStr = name;
                quId = quIds.get(position);
                Intent i2 = new Intent(AddAddressChooseActivity.this, AddBuyAddressActivity.class);
                i2.putExtra("address", shengStr + " " + shiStr + " " + quStr);
                i2.putExtra("shengID", shengId);
                i2.putExtra("shiID", shiId);
                i2.putExtra("quID", quId);
                setResult(RESULT_OK, i2);
                finish();
            }
        });
    }

    @OnClick({R.id.my_set_adresschoose_sheng_2, R.id.my_set_adresschoose_sheng_3, R.id.my_set_adresschoose_shi_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_set_adresschoose_sheng_2:
                mySetAdresschoose1.setVisibility(View.VISIBLE);
                mySetAdresschoose2.setVisibility(View.GONE);
                mySetAdresschoose3.setVisibility(View.GONE);
                break;
            case R.id.my_set_adresschoose_sheng_3:
                mySetAdresschoose1.setVisibility(View.VISIBLE);
                mySetAdresschoose2.setVisibility(View.GONE);
                mySetAdresschoose3.setVisibility(View.GONE);
                break;
            case R.id.my_set_adresschoose_shi_3:
                mySetAdresschoose1.setVisibility(View.GONE);
                mySetAdresschoose2.setVisibility(View.VISIBLE);
                mySetAdresschoose3.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void getShengList(AreaList model) {
        if (model.getCode() == 200) {
            shengObject = model;
            Message msgMessage = handler.obtainMessage();
            msgMessage.what = 1;
            handler.sendMessage(msgMessage);
        }
    }

    @Override
    public void getShiList(AreaList model) {
        if (model.getCode() == 200) {
            shiObject = model;
            Message msgMessage = handler.obtainMessage();
            msgMessage.what = 2;
            handler.sendMessage(msgMessage);
        }
    }

    @Override
    public void getQuList(AreaList model) {
        if (model.getCode() == 200) {
            quObject = model;
            Message msgMessage = handler.obtainMessage();
            msgMessage.what = 3;
            handler.sendMessage(msgMessage);
        }
    }


    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(AddAddressChooseActivity.this, "获取数据中...", false, null);
    }

    @Override
    public void hidProgressDialog() {
        CustomProgress.dissmiss();
    }

    @Override
    public void showError(String error) {
        CustomProgress.dissmiss();
    }
}
