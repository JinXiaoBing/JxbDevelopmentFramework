package com.jinxb.developmentframework.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class BaseDialogUtils {
    private static ProgressDialog mProgressDialog;

    /*------------------------------------------------------------------------------*/

    /**
     * 可手动取消的确认dialog
     */
    public static void showConfirmDialog(Context context, String msg,
                                          OnClickListener confirmListener,
                                          OnClickListener cancelListener) {
        showConfirmDialog(context, msg, true, confirmListener, cancelListener);
    }

    public static void showConfirmDialog(Context context, String msg,
                                         OnClickListener confirmListener) {
        showConfirmDialog(context, msg, confirmListener, null);
    }

    /**
     * 不可手动取消的确认dialog
     */
    public static void showConfirmDialogUnCancelable(Context context, String msg,
                                                      OnClickListener confirmListener,
                                                      OnClickListener cancelListener) {
        showConfirmDialog(context, msg, false, confirmListener, cancelListener);
    }

    public static void showConfirmDialogUnCancelable(Context context, String msg,
                                                     OnClickListener confirmListener) {
        showConfirmDialogUnCancelable(context, msg, confirmListener, null);
    }

    /**
     * 确认dialog
     */
    private static void showConfirmDialog(Context context, String msg, boolean cancelable,
                                          OnClickListener confirmListener,
                                          OnClickListener cancelListener) {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage(msg)
                .setCancelable(cancelable)
                .setPositiveButton("确定", (dialog, i) -> {
                    if (confirmListener != null) {
                        confirmListener.onClick();
                    }
                    dialog.dismiss();
                })
                .setNegativeButton("取消", (dialog, i) -> {
                    if (cancelListener != null) {
                        cancelListener.onClick();
                    }
                    dialog.dismiss();
                })
                .show();
    }
    /*------------------------------------------------------------------------------*/


    private static boolean isLiving(Activity activity) {

        if (activity == null) {
            Log.d("wisely", "activity == null");
            return false;
        }

        if (activity.isFinishing()) {
            Log.d("wisely", "activity is finishing");
            return false;
        }
        return true;
    }
    public static void showProgressDialog(Activity context) {
        showProgressDialog(context, "", "",true);
    }
    public static void showProgressDialogUnCancelable(Activity context) {
        showProgressDialog(context, "", "",false);
    }
    public static void showProgressDialog(Activity context,String title, String msg) {
        showProgressDialog(context, title, msg,true);
    }
    public static void showProgressDialogUnCancelable(Activity context,String title, String msg) {
        showProgressDialog(context, title, msg,false);
    }
    /**
     * 进度dialog
     */
    private static void showProgressDialog(Activity activity, String title, String msg,boolean cancelable) {
        if (!isLiving(activity)) {
            return;
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(activity);
        }
        //依次设置标题,内容,是否用取消按钮关闭,是否显示进度
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(cancelable);
        //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setIndeterminate(true);
        //调用show()方法将ProgressDialog显示出来
        mProgressDialog.show();
    }



    public static void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }

    /*------------------------------------------------------------------------------*/

    /**
     * 提示dialog
     */
    public static void showInfoDialog(Context context, String msg) {
        showInfoDialog(context, msg, true, null);
    }
    /**
     * 提示dialog
     */
    public static void showInfoDialog(Context context, String msg ,OnClickListener onClickListener) {
        showInfoDialog(context, msg, true, onClickListener);
    }
    /**
     * 不可手动取消的提示dialog
     */
    public static void showInfoDialogUnCancelable(Context context, String msg) {
        showInfoDialog(context, msg, false, null);
    }
    /**
     * 不可手动取消的提示dialog
     */
    public static void showInfoDialogUnCancelable(Context context, String msg,OnClickListener onClickListener) {
        showInfoDialog(context, msg, false, onClickListener);
    }
    private static void showInfoDialog(Context context, String msg, boolean cancelable, OnClickListener onClickListener) {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage(msg)
                .setCancelable(cancelable)
                .setPositiveButton("确定", (dialog, i) -> {
                    if (onClickListener != null) {
                        onClickListener.onClick();
                    }
                    dialog.dismiss();
                }).show();
    }
    /*------------------------------------------------------------------------------*/

    /**
     * 清除所有dialog，在activity退出之前调用
     */
    public static void dismissAll() {
        dismissProgressDialog();
    }

    public interface OnClickListener {
        void onClick();
    }
}
