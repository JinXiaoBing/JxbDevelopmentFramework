package com.jinxb.developmentframework.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.jinxb.developmentframework.utils.BaseDialogUtils;

/**
 * Created by guoqingqing on 2017/5/10.
 */

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private Intent mIntent;
    private boolean isFront = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFront = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFront = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(RESULT_OK);
        BaseDialogUtils.dismissAll();
    }

    public void showInfoDialog(String msg) {
        BaseDialogUtils.showInfoDialog(this, msg);
    }

    public void showInfoDialog(String msg, BaseDialogUtils.OnClickListener onClickListener) {
        BaseDialogUtils.showInfoDialog(this, msg, onClickListener);
    }

    public void showConfirmDialog(String msg, BaseDialogUtils.OnClickListener listener) {
        BaseDialogUtils.showConfirmDialog(this, msg, listener);
    }

    public void showConfirmDialog(String msg,
                                  BaseDialogUtils.OnClickListener confirmListener,
                                  BaseDialogUtils.OnClickListener cancelListener) {
        BaseDialogUtils.showConfirmDialog(this, msg, confirmListener, cancelListener);
    }

    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    public void goActivity(Class<? extends Activity> className) {
        mIntent = new Intent(this, className);
        startActivity(mIntent);
    }

    public void goActivityWithData(Class<? extends Activity> className, Bundle b) {
        mIntent = new Intent(this, className);
        mIntent.putExtras(b);
        startActivity(mIntent);
    }
    public void goActivityForResultWithData(Class<? extends Activity> className,
                                            int requestCode, Bundle b) {
        mIntent = new Intent(this, className);
        mIntent.putExtras(b);
        startActivityForResult(mIntent, requestCode);
    }

    public void goActivityForResult(Class<? extends Activity> className,
                                    int requestCode) {
        mIntent = new Intent(this, className);
        startActivityForResult(mIntent, requestCode);
    }

}
