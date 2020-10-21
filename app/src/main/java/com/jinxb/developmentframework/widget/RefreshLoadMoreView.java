package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jixb.developmentframework.R;

public class RefreshLoadMoreView extends BaseSwipeToLoadView {

    public RefreshLoadMoreView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_refresh_loadmore_view;
    }

}
