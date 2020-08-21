package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.jixb.developmentframework.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRadioGroup extends FrameLayout {

    private final Context context;
    private TextView mTvTitle;
    private RadioGroup mGroup;
    private int textSize;
    private List<RadioButton> buttons;

    public CustomRadioGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.common_radio_group_layout, this);
        mTvTitle = findViewById(R.id.tv_title);
        mGroup = findViewById(R.id.group);
        buttons = new ArrayList<>();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CustomRadioGroup);
        String title = typedArray.getString(R.styleable.CustomRadioGroup_text_title);
        textSize =
                typedArray.getDimensionPixelSize(R.styleable.CustomRadioGroup_text_size
                        , getResources().getDimensionPixelSize(R.dimen.text_size_normal));
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    public void addButton(String btnText ) {
        RadioButton tempButton = new RadioButton(context);
        tempButton.setText(btnText);
        tempButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mGroup.addView(tempButton);
        buttons.add(tempButton);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton btn = findViewById(checkedId);
            listener.checked(btn.getText());
        });
    }

    public void setChecked(String title) {
        for (int i = 0; i < buttons.size(); i++) {
            if (TextUtils.equals(title, buttons.get(i).getText().toString())) {
                buttons.get(i).setChecked(true);
                return;
            }
        }
    }

    public interface OnCheckedChangeListener {
        void checked(Object tag);
    }
}
