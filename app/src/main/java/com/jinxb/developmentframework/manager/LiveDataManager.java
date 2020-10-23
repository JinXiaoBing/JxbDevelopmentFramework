package com.jinxb.developmentframework.manager;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.jinxb.developmentframework.data.ConfirmDialogBean;
import com.jinxb.developmentframework.data.InfoDialogBean;

public class LiveDataManager implements DefaultLifecycleObserver {
    public MutableLiveData<String> toastLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorMsgLiveData = new MutableLiveData<>();
    public MutableLiveData<String> processMsgLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> dismissProcessDialogLiveData = new MutableLiveData<>();
    public MutableLiveData<InfoDialogBean> infoDialogLiveData = new MutableLiveData<>();
    public MutableLiveData<ConfirmDialogBean> confirmDialogLiveData = new MutableLiveData<>();


    private LiveDataManager() {

    }

    public static LiveDataManager getInstance() {
        return ManagerHolder.mInstance;
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        toastLiveData.removeObservers(owner);
        errorMsgLiveData.removeObservers(owner);
        processMsgLiveData.removeObservers(owner);
        dismissProcessDialogLiveData.removeObservers(owner);
        infoDialogLiveData.removeObservers(owner);
        confirmDialogLiveData.removeObservers(owner);
    }

    static class ManagerHolder {
        public static LiveDataManager mInstance = new LiveDataManager();
    }
}
