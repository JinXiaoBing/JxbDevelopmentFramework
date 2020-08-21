package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.jixb.developmentframework.R;


public class CustomAppBar extends FrameLayout {
    private RelativeLayout mLayoutLeft;
    private TextView mTvLeft;
    private TextView mTvTitle;
    private RelativeLayout mLayoutRight;
    private TextView mTvRight;
    private ImageView mIvBack;


    public CustomAppBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_titlebar_layout, this);
        mLayoutLeft = findViewById(R.id.layout_left);
        mTvLeft = findViewById(R.id.tv_left);
        mTvTitle = findViewById(R.id.tv_title);
        mLayoutRight = findViewById(R.id.layout_right);
        mIvBack = findViewById(R.id.iv_back);
        mTvRight = findViewById(R.id.tv_right);
        initAttrs(context, attrs);
    }

    @BindingAdapter("bindingRightStr")
    public static void bindingRightStr(CustomAppBar view, String str) {
        view.setRightStr(str);
    }
    @BindingAdapter("bindingRightBtnVisible")
    public static void bindingRightBtnVisible(CustomAppBar view, boolean visible) {
        view.setRightBtnVisible(visible);
    }

    private void setRightBtnVisible(boolean visible) {
        mLayoutRight.setVisibility(visible? View.VISIBLE: View.GONE);
    }

    @BindingAdapter("bindingLeftOnClick")
    public static void bindingLeftOnClick(CustomAppBar view, OnClickListener listener) {
        view.setLeftOnClick(listener);
    }

    @BindingAdapter("bindingRightOnClick")
    public static void bindingRightOnClick(CustomAppBar view, OnClickListener listener) {
        view.setRightOnClick(listener);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CustomAppBar);
        String title = typedArray.getString(R.styleable.CustomAppBar_appbar_title);
        String rightStr = typedArray.getString(R.styleable.CustomAppBar_appbar_rightStr);
        String leftStr = typedArray.getString(R.styleable.CustomAppBar_appbar_leftStr);
        boolean visibility =
                typedArray.getBoolean(R.styleable.CustomAppBar_appbar_backBtnVisible,
                        false);
        boolean rightBtnVisibility =
                typedArray.getBoolean(R.styleable.CustomAppBar_appbar_rightBtnVisible,
                        false);
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(rightStr)) {
            mTvRight.setText(rightStr);
        }
        if (!TextUtils.isEmpty(leftStr)) {
            mTvLeft.setText(leftStr);
        }
        mIvBack.setVisibility(visibility ? VISIBLE : GONE);
        mLayoutRight.setVisibility(rightBtnVisibility ? VISIBLE : GONE);
    }

    public void setOnAppBarLeftClick(OnClickListener listener) {
        mLayoutLeft.setOnClickListener(listener);
    }

    public void setOnAppBarRightClick(OnClickListener listener) {
        mLayoutRight.setOnClickListener(listener);
    }

    private void setRightOnClick(OnClickListener listener) {
        mLayoutRight.setOnClickListener(listener);
    }

    private void setLeftOnClick(OnClickListener listener) {
        mLayoutLeft.setOnClickListener(listener);
    }

    private void setRightStr(String str) {
        mTvRight.setText(str);
    }
}
