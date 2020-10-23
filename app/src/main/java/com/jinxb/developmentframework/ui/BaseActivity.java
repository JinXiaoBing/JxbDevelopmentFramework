package com.jinxb.developmentframework.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.BarUtils;
import com.jinxb.developmentframework.domain.manager.NetworkStateManager;
import com.jinxb.developmentframework.manager.LiveDataManager;
import com.jinxb.developmentframework.ui.page.DataBindingActivity;
import com.jinxb.developmentframework.utils.BaseDialogUtils;


/**
 * Created by guoqingqing on 2017/5/10.
 */

public abstract class BaseActivity extends DataBindingActivity {
    protected BaseViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(this, true);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getLifecycle().addObserver(NetworkStateManager.getInstance());
        getLifecycle().addObserver(LiveDataManager.getInstance());
    }

    protected void initObserve() {


    }

    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        return super.getActivityViewModel(modelClass);
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return super.getAppViewModelProvider();
    }


    @Override
    protected void onResume() {
        super.onResume();
        LiveDataManager.getInstance().toastLiveData.observe(this, s -> showShortToast(s));
        LiveDataManager.getInstance().errorMsgLiveData.observe(this, msg -> showInfoDialog(msg));
        LiveDataManager.getInstance().dismissProcessDialogLiveData.observe(this, aBoolean -> {
            if (aBoolean) {
                BaseDialogUtils.dismissProgressDialog();
            }
        });
        LiveDataManager.getInstance().processMsgLiveData
                .observe(this, msg -> BaseDialogUtils.showProgressDialog(this, "", msg));
        LiveDataManager.getInstance().infoDialogLiveData.observe(this, infoDialogBean ->
                showInfoDialog(infoDialogBean.getMsg(), infoDialogBean.getOnConfirmListener())
        );
        LiveDataManager.getInstance().confirmDialogLiveData
                .observe(this, confirmDialogBean ->
                        showConfirmDialog(confirmDialogBean.getMsg(), confirmDialogBean.getOnConfirmListener(),
                                confirmDialogBean.getOnCancelListener()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseDialogUtils.dismissAll();
    }

    protected void showInfoDialog(String msg) {
        BaseDialogUtils.showInfoDialog(this, msg);
    }

    protected void showInfoDialog(String msg, BaseDialogUtils.OnClickListener listener) {
        BaseDialogUtils.showInfoDialog(this, msg, listener);
    }


    public void showConfirmDialog(String msg,
                                  BaseDialogUtils.OnClickListener onClickListener) {
        BaseDialogUtils.showConfirmDialog(this, msg, onClickListener);
    }

    public void showConfirmDialog(String msg,
                                  BaseDialogUtils.OnClickListener onConfirmListener,
                                  BaseDialogUtils.OnClickListener onCancelListener) {
        BaseDialogUtils.showConfirmDialog(this, msg, onConfirmListener, onCancelListener);
    }

    public void goActivity(Class<? extends Activity> className) {
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }

    public void goActivityWithData(Class<? extends Activity> className, Bundle b) {
        Intent mIntent = new Intent(this, className);
        mIntent.putExtras(b);
        startActivity(mIntent);
    }

    public void goActivityForResult(Class<? extends Activity> className,
                                    int requestCode) {
        Intent intent = new Intent(this, className);
        startActivityForResult(intent, requestCode);
    }
}
