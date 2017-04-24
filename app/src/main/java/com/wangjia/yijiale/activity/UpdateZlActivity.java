package com.wangjia.yijiale.activity;


/**
 * 设置
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wangjia.yijiale.R;
import com.wangjia.yijiale.YiApplication;
import com.wangjia.yijiale.entity.Login;
import com.wangjia.yijiale.iview.UpdateZlActivityView;
import com.wangjia.yijiale.presenter.UpdateZlActivityPresenter;
import com.wangjia.yijiale.presenter.impl.UpdateZlActivityPresenterImpl;
import com.wangjia.yijiale.utils.CircleImageView;
import com.wangjia.yijiale.utils.Config;
import com.wangjia.yijiale.utils.Constants;
import com.wangjia.yijiale.utils.FileUtils;
import com.wangjia.yijiale.utils.ImageUtil;
import com.wangjia.yijiale.utils.L;
import com.wangjia.yijiale.utils.SPUtils;
import com.wangjia.yijiale.utils.StringFunction;
import com.wangjia.yijiale.utils.Titlebulder;
import com.wangjia.yijiale.views.CustomProgress;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UpdateZlActivity extends AppCompatActivity implements UpdateZlActivityView {
    @Bind(R.id.nichen)
    EditText nichen;
    @Bind(R.id.r_safe)
    RelativeLayout rSafe;
    @Bind(R.id.user_head_civ)
    CircleImageView userHeadCiv;
    @Bind(R.id.man_sex_rb)
    RadioButton manSexRb;
    @Bind(R.id.woman_sex_rb)
    RadioButton womanSexRb;
    @Bind(R.id.sex_rg)
    RadioGroup sexRg;
    @Bind(R.id.age)
    EditText age;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.real_name)
    EditText realName;
    @Bind(R.id.ic_card)
    EditText icCard;
    @Bind(R.id.recommend)
    RelativeLayout recommend;
    @Bind(R.id.info_save)
    Button infoSave;
    private Intent i;
    /***
     * 拍照的intent 发起code 4
     */
    private static final int CAMERA_REQUEST_CODE = 2;
    private AlertView mAlertView;
    /**
     * 4.4以下(也就是kitkat以下)的版本
     */
    public static final int KITKAT_LESS = 3;
    /**
     * 4.4以上(也就是kitkat以上)的版本,当然也包括最新出的5.0棒棒糖
     */
    public static final int KITKAT_ABOVE = 4;
    File newPhoto = null;
    private String capturePath;
    private int sex = 0;
    private UpdateZlActivityPresenter updateZlActivityPresenter;
    private List<File> my_file_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zl);
        i = new Intent();
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {
        updateZlActivityPresenter = new UpdateZlActivityPresenterImpl(this, this);

        nichen.setText(SPUtils.get(UpdateZlActivity.this, Constants.NiChen, "").toString());
        nichen.setSelection(SPUtils.get(UpdateZlActivity.this, Constants.NiChen, "").toString().length());

        int sage = (int) SPUtils.get(UpdateZlActivity.this, Constants.Age, 0);
        if (sage != 0) {
            age.setText(String.valueOf(sage));
        }

        realName.setText(String.valueOf(SPUtils.get(UpdateZlActivity.this, Constants.RealName, "")));

        icCard.setText(String.valueOf(SPUtils.get(UpdateZlActivity.this, Constants.CardNum, "")));

        String s_avatar = SPUtils.get(UpdateZlActivity.this, Constants.MEMBER_AVATAR, "").toString();
        if(StringFunction.isNotNull(s_avatar)) {
            Glide.with(this).load(s_avatar).into(userHeadCiv);
        }

        if ((int) SPUtils.get(UpdateZlActivity.this, Constants.Sex, 0) == 1) {
            manSexRb.setChecked(true);
            womanSexRb.setChecked(false);
            sex = 1;
        } else if ((int) SPUtils.get(UpdateZlActivity.this, Constants.Sex, 0) == 2) {
            womanSexRb.setChecked(true);
            manSexRb.setChecked(false);
            sex = 2;
        } else {
            manSexRb.setChecked(false);
            womanSexRb.setChecked(false);

        }
    }

    public void initView() {
        new Titlebulder(this)
                .setLeftImage(R.mipmap.arrowleft)
                .setTitleName("个人资料")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        sexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.man_sex_rb) {
                    sex = 1;
                } else if (checkedId == R.id.woman_sex_rb) {
                    sex = 2;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.info_save, R.id.user_head_civ})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.info_save:
                if (my_file_list.size() == 1) {
                    updateZlActivityPresenter.getData(my_file_list.get(0), sex, Integer.parseInt(age.getText().toString()),
                            icCard.getText().toString(), realName.getText().toString(), nichen.getText().toString());
                } else {
                    updateZlActivityPresenter.getData(null, sex, Integer.parseInt(age.getText().toString()),
                            icCard.getText().toString(), realName.getText().toString(), nichen.getText().toString());
                }
                break;
            case R.id.user_head_civ:
                if (mAlertView == null) {
                    mAlertView = new AlertView("更换头像", null, "取消", null,
                            new String[]{"拍照", "从相册选取"},
                            this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object v, int position) {

                            if (position == 0) {//拍照
                                takeAPicture();
                            } else if (position == 1) {//相册选择
                                selectPicture();
                            } else {//取消
                                mAlertView.dismiss();
                            }

                        }
                    });
                }
                mAlertView.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            try {
                File file = null;
                Uri uri = null;
                my_file_list.clear();
                switch (requestCode) {
                    case CAMERA_REQUEST_CODE:
                        Picasso
                                .with(UpdateZlActivity.this)
                                .load(newPhoto)
                                .into(userHeadCiv);
                        my_file_list.add(newPhoto);
                        uploadImage(newPhoto.getAbsolutePath());
                        break;
                    case KITKAT_ABOVE://4.4以上
                        uri = data.getData();
                        if (uri == null) {
                            Bundle bundle = data.getExtras();
                            if (bundle != null) {
                                Bitmap photo = (Bitmap) bundle.get("data");
                                File files = new File(getExternalCacheDir(), YiApplication.TEMP_FILE_NAME);
                                if (!files.exists()) {
                                    files.mkdirs();
                                }
                                capturePath = files.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
                                ImageUtil.saveBitMap(photo, capturePath);
                                Picasso.with(UpdateZlActivity.this)
                                        .load(new File(capturePath))
                                        .into(userHeadCiv);
                                my_file_list.add(new File(capturePath));
                                uploadImage(capturePath);
                            }
                        } else {
                            String thePath = ImageUtil.getPathAfterKitKat(this, uri);
                            Picasso.with(UpdateZlActivity.this)
                                    .load(new File(thePath))
                                    .into(userHeadCiv);
                            my_file_list.add(new File(thePath));
                            uploadImage(thePath);
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void uploadImage(String thePath) {
        OkHttpUtils.post().url(Config.URI + "mobileapp/index.php?act=apimember&op=upMemberInFo")
                .addFile("member_avatar", "useravater.jpg", new File(thePath))
                .addParams("token", YiApplication.getInstance().getToken())
                .addParams("member_sex", sex + "")
                .addParams("member_age", age.getText().toString())
                .addParams("member_idcard", icCard.getText().toString())
                .addParams("member_truename", realName.getText().toString())
                .addParams("member_nickname", nichen.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        L.e("上传失败！");
                    }

                    @Override
                    public void onResponse(String response) {
                        Login model = new Gson().fromJson(response, Login.class);
                        if(model.getDatas()==null){
                            return;
                        }
                        SPUtils.put(UpdateZlActivity.this, Constants.NiChen, model.getDatas().getMember_nickname());
                        SPUtils.put(UpdateZlActivity.this, Constants.Sex, model.getDatas().getMember_sex());
                        SPUtils.put(UpdateZlActivity.this, Constants.Age, model.getDatas().getMember_age());
                        SPUtils.put(UpdateZlActivity.this, Constants.RealName, model.getDatas().getMember_truename());
                        SPUtils.put(UpdateZlActivity.this, Constants.CardNum, model.getDatas().getMember_idcard());
                        SPUtils.put(UpdateZlActivity.this, Constants.MEMBER_AVATAR, model.getDatas().getMember_avatar());
//                        finish();
                        L.e("上传成功！" + model.getDatas().getMember_avatar());
                    }
                });
    }

    /**
     * description:从相册中选择图片
     */
    public void selectPicture() {
        getNewFileUri();
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                Intent intent = new Intent();
                intent.setType("image/*");
                // intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, KITKAT_LESS);
            } else if (Build.MANUFACTURER.equals("Xiaomi")) {// 小米手机优化了还是4.4前版本处理方式
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                // intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, KITKAT_LESS);
            } else {
                Intent intent = new Intent();
                intent.setType("image/*");
                // intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setAction(Intent.ACTION_PICK);// 从本地相册读取文件
                startActivityForResult(intent, KITKAT_ABOVE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 拍照
     */
    public void takeAPicture() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getNewFileUri());
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }


    /***
     * 创建存储临时照片地方
     *
     * @return
     */
    public Uri getNewFileUri() {

        try {
            newPhoto = FileUtils.getInstance().newTempImageFile();
        } catch (Exception e) {

            Log.e("uartist", e.getMessage());
        }
        return Uri.fromFile(newPhoto);
    }

    @Override
    public void getData(Login model) {
        L.TShort(UpdateZlActivity.this, model.getMsg());
        if (model.getCode() == 200) {
            SPUtils.put(UpdateZlActivity.this, Constants.NiChen, model.getDatas().getMember_nickname());
            SPUtils.put(UpdateZlActivity.this, Constants.Sex, model.getDatas().getMember_sex());
            SPUtils.put(UpdateZlActivity.this, Constants.Age, model.getDatas().getMember_age());
            SPUtils.put(UpdateZlActivity.this, Constants.RealName, model.getDatas().getMember_truename());
            SPUtils.put(UpdateZlActivity.this, Constants.CardNum, model.getDatas().getMember_idcard());
            SPUtils.put(UpdateZlActivity.this, Constants.MEMBER_AVATAR, model.getDatas().getMember_avatar());
            finish();
        }

    }

    @Override
    public void showProgressDialog() {
        CustomProgress.showProgress(UpdateZlActivity.this, "获取数据中...", false, null);
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
