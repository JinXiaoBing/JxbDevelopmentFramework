package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.jixb.developmentframework.R;


public class CustomLineTextView extends FrameLayout {
    private TextView mTvTitle;
    private TextView mTvTitle2;
    private TextView mTvTitle3;
    private TextView mTvContent;
    private TextView mTvContent2;
    private LinearLayout mLayoutLine;
    private ImageView mIvArrow;


    public CustomLineTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_line_text_view, this);
        mLayoutLine = findViewById(R.id.layout_line);
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle2 = findViewById(R.id.tv_title2);
        mTvTitle3 = findViewById(R.id.tv_title3);
        mTvContent = findViewById(R.id.tv_content);
        mTvContent2 = findViewById(R.id.tv_content2);
        mIvArrow = findViewById(R.id.iv_arrow);
        initAttrs(context, attrs);
    }

    @BindingAdapter("bindTitle")
    public static void setTitle(CustomLineTextView view, String title) {
        view.setTitle(title);
    }
    @BindingAdapter("bindTitle2")
    public static void setTitle2(CustomLineTextView view, String title) {
        view.setTitle2(title);
    }
    @BindingAdapter("bindTitle3")
    public static void setTitle3(CustomLineTextView view, String title) {
        view.setTitle3(title);
    }
    @BindingAdapter("bindContent")
    public static void setContent(CustomLineTextView view, String content) {
        view.setContent(content);
    }

    @BindingAdapter("bindContent2")
    public static void setContent2(CustomLineTextView view, String content) {
        view.setContent2(content);
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
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        mTvTitle2.setText(typedArray.getString(R.styleable.CustomLineTextView_text_title2));
        mTvTitle2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        mTvTitle3.setText(typedArray.getString(R.styleable.CustomLineTextView_text_title3));
        mTvTitle3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        mTvContent.setText(typedArray.getString(R.styleable.CustomLineTextView_text_content));
        mTvContent.setHint(typedArray.getString(R.styleable.CustomLineTextView_text_hint));
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        mTvContent2.setText(typedArray.getString(R.styleable.CustomLineTextView_text_content2));
        mTvContent2.setHint(typedArray.getString(R.styleable.CustomLineTextView_text_hint2));
        mTvContent2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        mIvArrow.setVisibility(typedArray.getBoolean(R.styleable.CustomLineTextView_showRightArrow, false) ? VISIBLE : GONE);
    }

    public void setContent2(String content) {
        mTvContent2.setText(content);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }
    public String getTitle(){
        return mTvTitle.getText().toString();
    }
    public void setTitle2(String title) {
        mTvTitle2.setText(title);
    }
    public void setTitle3(String title) {
        mTvTitle3.setText(title);
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

    public void setHint(String hint) {
        mTvContent.setHint(hint);
    }
}
