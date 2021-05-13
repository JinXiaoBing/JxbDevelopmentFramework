package com.jinxb.developmentframework.utils;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:    ChenZheng
 * Date:      2018/7/30
 * Copyrights: 江苏中天科技软件技术有限公司
 * Description: 时间选择器
 */
public class PickerViewUtils {
    /**
     * 日期选择器（年月日）
     */
    public static void showDatePickViewYMD(Context context, OnTimeSelectListener timeSelectListener) {
        boolean[] type = {true, true, true, false, false, false};
        showDateTimePickView(type, context, "请选择", timeSelectListener);
    }

    /**
     * 时间选择器（年月日）
     */
    public static void showDatePickViewYMD(Context context, String title, OnTimeSelectListener timeSelectListener) {
        boolean[] type = {true, true, true, false, false, false};
        showDateTimePickView(type, context, title, timeSelectListener);
    }

    /**
     * 时间选择器（时分秒）
     */
    public static void showTimePickViewHMS(Context context, OnTimeSelectListener timeSelectListener) {
        boolean[] type = {false, false, false, true, true, true};
        showDateTimePickView(type, context, "请选择", timeSelectListener);
    }

    /**
     * 日期选择器（时分秒）
     */
    public static void showTimePickViewHMS(Context context, String title, OnTimeSelectListener timeSelectListener) {
        boolean[] type = {false, false, false, true, true, true};
        showDateTimePickView(type, context, title, timeSelectListener);
    }

    public static void showDateTimePickView(Context context, OnTimeSelectListener timeSelectListener) {
        boolean[] type = {true, true, true, true, true, true};
        showDateTimePickView(type, context, "请选择", timeSelectListener);
    }

    public static void showDateTimePickView(Context context, String title, OnTimeSelectListener timeSelectListener) {
        boolean[] type = {true, true, true, true, true, true};
        showDateTimePickView(type, context, title, timeSelectListener);
    }

    /**
     * 日期时间选择器（时分秒）
     */
    private static void showDateTimePickView(boolean[] type, Context ctx, String title,
                                             OnTimeSelectListener timeSelectListener) {
        new TimePickerBuilder(ctx, timeSelectListener)
                .setType(type)// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .build().show();
    }
    /*-------------------------------------------------------------------------------------------------------*/

    /**
     * 底部显示String类型的pickview
     */
    public static void showPickViewBottom(Context context, List<String> options1Items,
                                          OnOptionsSelectListener selectListener) {
        showPickViewBottom(context, "请选择", options1Items, selectListener);
    }

    /**
     * 底部显示String类型的pickview
     */
    public static void showPickViewBottom(Context context, String title, List<String> options1Items,
                                          OnOptionsSelectListener selectListener) {
        showPickView(context, title, options1Items, false, selectListener, null);
    }

    /**
     * 以dialog形式显示String类型的pickview
     */
    public static void showPickViewDialog(Context context, List<String> options1Items,
                                          OnOptionsSelectListener selectListener) {
        showPickViewDialog(context, "请选择", options1Items, selectListener);
    }

    /**
     * 以dialog形式显示String类型的pickview
     */
    public static void showPickViewDialog(Context context, String title, List<String> options1Items,
                                          OnOptionsSelectListener selectListener) {
        showPickView(context, title, options1Items, true, selectListener, null);
    }

    /**
     * 底部显示pickview
     */
    public static <T> void showPickViewBottom(Context context, List<T> options1Items,
                                              OnOptionsSelectListener selectListener,
                                              OnDataTransform onDataTransform) {
        showPickViewBottom(context, "请选择", options1Items, selectListener, onDataTransform);
    }

    /**
     * 底部显示pickview
     */
    public static <T> void showPickViewBottom(Context context, String title, List<T> options1Items,
                                              OnOptionsSelectListener selectListener,
                                              OnDataTransform onDataTransform) {
        showPickView(context, title, options1Items, false, selectListener, onDataTransform);
    }

    /**
     * 以dialog形式显示pickview
     */
    public static <T> void showPickViewDialog(Context context, List<T> options1Items,
                                              OnOptionsSelectListener selectListener, OnDataTransform onDataTransform) {
        showPickViewDialog(context, "请选择", options1Items, selectListener, onDataTransform);
    }

    /**
     * 以dialog形式显示pickview
     */
    public static <T> void showPickViewDialog(Context context, String title, List<T> options1Items,
                                              OnOptionsSelectListener selectListener, OnDataTransform onDataTransform) {
        showPickView(context, title, options1Items, true, selectListener, onDataTransform);
    }

    private static <T> void showPickView(Context context, String title, @NonNull List<T> options1Items, boolean isDialog,
                                         OnOptionsSelectListener selectListener,
                                         OnDataTransform onDataTransform) {
        OptionsPickerView<String> pvOptions = new OptionsPickerBuilder(context, selectListener)
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .setTitleColor(Color.BLACK)//标题文字颜色
                .isDialog(isDialog)
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .build();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < options1Items.size(); i++) {
            T t = options1Items.get(i);
            String item = t instanceof String ? (String) t : onDataTransform.onDataTransform(t);
            items.add(item);
        }
        pvOptions.setPicker(items);//添加数据源
        pvOptions.show();
    }

    public interface OnDataTransform<T> {
        String onDataTransform(T item);
    }
}
