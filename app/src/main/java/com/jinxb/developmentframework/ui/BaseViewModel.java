package com.jinxb.developmentframework.ui;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jinxb.developmentframework.data.ConfirmDialogBean;
import com.jinxb.developmentframework.data.InfoDialogBean;

public class BaseViewModel extends ViewModel {
    private MutableLiveData<String> toastMsgLiveData;
    private MutableLiveData<InfoDialogBean> infoDialogLiveData;
    private MutableLiveData<ConfirmDialogBean> confirmDialogLiveData;

    public LiveData<String> getToastMsgLiveData() {
        if (toastMsgLiveData==null) {
            toastMsgLiveData = new MutableLiveData<>();
        }
        return toastMsgLiveData;
    }

    public LiveData<InfoDialogBean> getInfoDialogLiveData() {
        if (infoDialogLiveData==null) {
            infoDialogLiveData = new MutableLiveData<>();
        }
        return infoDialogLiveData;
    }

    public LiveData<ConfirmDialogBean> getConfirmDialogLiveData() {
        if (confirmDialogLiveData==null) {
            confirmDialogLiveData = new MutableLiveData<>();
        }
        return confirmDialogLiveData;
    }


}
