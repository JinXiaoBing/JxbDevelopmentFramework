package com.jinxb.developmentframework.manager;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.jinxb.developmentframework.data.ConfirmDialogBean;
import com.jinxb.developmentframework.data.InfoDialogBean;
import com.jinxb.developmentframework.ui.callback.UnPeekLiveData;

public class LiveDataManager implements DefaultLifecycleObserver {
    public UnPeekLiveData<String> toastLiveData = new UnPeekLiveData<>();
    public UnPeekLiveData<String> errorMsgLiveData = new UnPeekLiveData<>();
    public UnPeekLiveData<String> processMsgLiveData = new UnPeekLiveData<>();
    public UnPeekLiveData<Boolean> dismissProcessDialogLiveData = new UnPeekLiveData<>();
    public UnPeekLiveData<InfoDialogBean> infoDialogLiveData = new UnPeekLiveData<>();
    public UnPeekLiveData<ConfirmDialogBean> confirmDialogLiveData = new UnPeekLiveData<>();


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
