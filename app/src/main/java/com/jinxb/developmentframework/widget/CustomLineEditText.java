package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.jixb.developmentframework.R;


public class CustomLineEditText extends FrameLayout {
    private LinearLayout mLayoutLine;
    private TextView mTvTitle;
    private EditText mTvContent;
    private TextView mTvContent2;

    public CustomLineEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_line_edit_text, this);
        mLayoutLine = findViewById(R.id.layout_line);
        mTvTitle = findViewById(R.id.tv_title);
        mTvContent = findViewById(R.id.tv_content);
        mTvContent2 = findViewById(R.id.tv_content2);
        initAttrs(context, attrs);
    }

    @BindingAdapter("bindTitle")
    public static void setTitle(CustomLineEditText view, String title) {
        view.setTitle(title);
    }

    @BindingAdapter("bindContent")
    public static void setContent(CustomLineEditText view, String content) {
        view.setContent(content);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray commonAttr = context.obtainStyledAttributes(attrs,
                R.styleable.CustomLineEditText);
        int textSize =
                commonAttr.getDimensionPixelSize(R.styleable.CustomLineEditText_text_size
                        , getResources().getDimensionPixelSize(R.dimen.text_size_normal));
        int minHeight =
                commonAttr.getDimensionPixelOffset(R.styleable.CustomLineEditText_min_height,
                        getResources().getDimensionPixelSize(R.dimen.dp_40));
        mLayoutLine.setMinimumHeight(minHeight);
        mTvTitle.setText(commonAttr.getString(R.styleable.CustomLineEditText_text_title));
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        mTvContent.setText(commonAttr.getString(R.styleable.CustomLineEditText_text_content));
        mTvContent.setHint(commonAttr.getString(R.styleable.CustomLineEditText_text_hint));
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTvContent.setImeActionLabel(
                commonAttr.getString(R.styleable.CustomLineEditText_imeActionLabel),
                commonAttr.getInt(R.styleable.CustomLineEditText_customImeOptions,0));

        mTvContent2.setText(commonAttr.getString(R.styleable.CustomLineEditText_text_content2));
        mTvContent2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

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

    public void addTextChangedListener(CustomTextWatcher textWatcher) {
        mTvContent.addTextChangedListener(textWatcher);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener listener) {
        mTvContent.setOnEditorActionListener(listener);
    }

    public abstract static class CustomTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }
}
