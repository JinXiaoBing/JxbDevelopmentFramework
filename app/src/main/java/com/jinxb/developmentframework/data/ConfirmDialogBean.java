package com.jinxb.developmentframework.data;


import com.jinxb.developmentframework.utils.BaseDialogUtils;

public class ConfirmDialogBean {
    private String msg;
    private BaseDialogUtils.OnClickListener onConfirmListener;
    private BaseDialogUtils.OnClickListener onCancelListener;

    public ConfirmDialogBean(String msg) {
        this.msg = msg;
    }

    public ConfirmDialogBean(String msg, BaseDialogUtils.OnClickListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public ConfirmDialogBean(String msg, BaseDialogUtils.OnClickListener onConfirmListener, BaseDialogUtils.OnClickListener onCancelListener) {
        this.msg = msg;
        this.onConfirmListener = onConfirmListener;
        this.onCancelListener = onCancelListener;
    }

    public String getMsg() {
        return msg;
    }

    public BaseDialogUtils.OnClickListener getOnConfirmListener() {
        return onConfirmListener;
    }

    public BaseDialogUtils.OnClickListener getOnCancelListener() {
        return onCancelListener;
    }
}
