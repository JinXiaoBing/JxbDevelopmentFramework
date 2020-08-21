package com.jinxb.developmentframework.data;


import com.jinxb.developmentframework.utils.BaseDialogUtils;

public class InfoDialogBean {
    private String msg;
    private BaseDialogUtils.OnClickListener onConfirmListener;

    public InfoDialogBean(String msg) {
        this.msg = msg;
    }

    public InfoDialogBean(String msg, BaseDialogUtils.OnClickListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public String getMsg() {
        return msg;
    }

    public BaseDialogUtils.OnClickListener getOnConfirmListener() {
        return onConfirmListener;
    }



}
