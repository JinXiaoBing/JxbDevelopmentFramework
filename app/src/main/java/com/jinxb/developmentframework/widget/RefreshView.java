package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jixb.developmentframework.R;


public class RefreshView extends BaseSwipeToLoadView {

    public RefreshView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_refresh_view;
    }
}
