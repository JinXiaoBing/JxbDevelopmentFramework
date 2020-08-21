package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jixb.developmentframework.R;


public class CustomLineAddTextView extends FrameLayout {
    private LinearLayout mLayoutLine;
    private TextView mTvTitle;
    private TextView mTvContent;
    private ImageButton mBtnAdd;

    public CustomLineAddTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_line_add_text_view, this);
        mLayoutLine = findViewById(R.id.layout_line);
        mTvTitle = findViewById(R.id.tv_title);
        mTvContent = findViewById(R.id.tv_content);
        mBtnAdd = findViewById(R.id.btn_add);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CustomLineTextView);
        int textSize =
                typedArray.getDimensionPixelSize(R.styleable.CustomLineTextView_text_size
                        , getResources().getDimensionPixelSize(R.dimen.text_size_normal));
        int minHeight =
                typedArray.getDimensionPixelOffset(R.styleable.CustomLineTextView_min_height,
                        getResources().getDimensionPixelSize(R.dimen.dp_40));
        mLayoutLine.setMinimumHeight(minHeight);
        mTvTitle.setText(typedArray.getString(R.styleable.CustomLineTextView_text_title));
        mTvContent.setText(typedArray.getString(R.styleable.CustomLineTextView_text_content));
        mTvContent.setHint(typedArray.getString(R.styleable.CustomLineTextView_text_hint));
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setOnAddClickListener(OnClickListener click) {
        mBtnAdd.setOnClickListener(click);
    }

}
