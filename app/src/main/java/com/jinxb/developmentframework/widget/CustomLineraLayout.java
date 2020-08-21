package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jixb.developmentframework.R;


public class CustomLineraLayout extends LinearLayout {
    private int textSize;

    public CustomLineraLayout(Context context) {
        super(context);
    }

    public CustomLineraLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLineraLayout(Context context, @Nullable AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void addView(View child) {
        super.addView(child);
    }


    public CustomLineraLayout addView(String key, String value) {
        return addView(key,value, R.color.black);
    }
    public CustomLineraLayout addView(String key, String value, int textColor) {
        View view = inflate(getContext(), R.layout.item_layout_add_record_item, null);
        TextView mTvKey = view.findViewById(R.id.tv_key);
        TextView mTvValue = view.findViewById(R.id.tv_value);
        mTvKey.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTvValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTvKey.setText(key);
        mTvValue.setText(value);
        mTvValue.setTextColor(getResources().getColor(textColor));
        addView(view);
        return this;
    }

    public CustomLineraLayout setClickListener( OnClickListener l) {
        super.setOnClickListener(l);
        return this;
    }

    public CustomLineraLayout setLongClickListener(OnLongClickListener l){
        super.setOnLongClickListener(l);
        return this;
    }

    public void setTestSize(int textSize) {
        this.textSize = textSize;
    }
}
