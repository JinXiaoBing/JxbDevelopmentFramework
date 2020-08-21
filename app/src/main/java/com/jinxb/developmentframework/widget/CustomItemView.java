package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jixb.developmentframework.R;


public class CustomItemView extends LinearLayout {
    public static final String TEXT_SIZE_SMALL = "small";
    public static final String TEXT_SIZE_NORMAL = "normal";
    public static final String TEXT_SIZE_BIG = "big";


    private TextView mTvKey;
    private TextView mTvValue;

    public CustomItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_layout_add_record_item, this);
        mTvKey = findViewById(R.id.tv_key);
        mTvValue = findViewById(R.id.tv_value);
    }

    public CustomItemView(Context context, String textSizeType) {
        this(context);
        int textSize = getResources().getDimensionPixelSize(R.dimen.text_size_normal);
        switch (textSizeType) {
            case TEXT_SIZE_SMALL:
                textSize =
                        getResources().getDimensionPixelSize(R.dimen.text_size_small);
                break;
            case TEXT_SIZE_NORMAL:
                textSize = getResources().getDimensionPixelSize(R.dimen.text_size_normal);
                break;
            case TEXT_SIZE_BIG:
                textSize =
                        getResources().getDimensionPixelSize(R.dimen.text_size_bigger);
                break;
        }
        mTvKey.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTvValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }


    public CustomItemView build(String key, String value) {
        mTvKey.setText(key);
        mTvValue.setText(value);
        return this;
    }
}
