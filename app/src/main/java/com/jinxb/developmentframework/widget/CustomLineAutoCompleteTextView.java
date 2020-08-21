package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jixb.developmentframework.R;


public class CustomLineAutoCompleteTextView extends FrameLayout {
    private LinearLayout mLayoutLine;
    private TextView mTvTitle;
    private AutoCompleteTextView mTvContent;

    public CustomLineAutoCompleteTextView(@NonNull Context context,
                                          @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_line_auto_complete_text_view, this);
        mLayoutLine = findViewById(R.id.layout_line);
        mTvTitle = findViewById(R.id.tv_title);
        mTvContent = findViewById(R.id.tv_content);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray commonAttr = context.obtainStyledAttributes(attrs,
                R.styleable.CustomLineAutoCompleteTextView);
        int textSize =
                commonAttr.getDimensionPixelSize(R.styleable.CustomLineAutoCompleteTextView_text_size
                        , getResources().getDimensionPixelSize(R.dimen.text_size_normal));
        int minHeight =
                commonAttr.getDimensionPixelOffset(R.styleable.CustomLineAutoCompleteTextView_min_height,
                        getResources().getDimensionPixelSize(R.dimen.dp_40));
        mLayoutLine.setMinimumHeight(minHeight);
        mTvTitle.setText(commonAttr.getString(R.styleable.CustomLineAutoCompleteTextView_text_title));
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        mTvContent.setText(commonAttr.getString(R.styleable.CustomLineAutoCompleteTextView_text_content));
        mTvContent.setHint(commonAttr.getString(R.styleable.CustomLineAutoCompleteTextView_text_hint));
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public String getContent() {
        return mTvContent.getText().toString();
    }

    public void setContent(String content) {
        mTvContent.setText(content);
    }

    public void setContent(int contentRes) {
        mTvContent.setText(contentRes);
    }

    public void setThreshold(int i) {
        mTvContent.setThreshold(i);
    }

    public void setAdapter(ArrayAdapter<String> arrayAdapter) {
        mTvContent.setAdapter(arrayAdapter);
    }
}
