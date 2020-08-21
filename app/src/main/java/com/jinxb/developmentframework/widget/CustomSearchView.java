package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.KeyboardUtils;
import com.jixb.developmentframework.R;

import java.util.List;

public class CustomSearchView extends FrameLayout {
    private final Context mContext;
    private TextView mTvTitle;
    private AutoCompleteTextView mEditKeyword;
    private OnSearchListener onSearchListener;

    public CustomSearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.common_search_layout, this);
        mTvTitle = findViewById(R.id.tv_title);
        mEditKeyword = findViewById(R.id.edit_keyword);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CustomSearchView);
        String title = typedArray.getString(R.styleable.CustomSearchView_text_title);
        int textSize =
                typedArray.getDimensionPixelSize(R.styleable.CustomSearchView_text_size
                        , getResources().getDimensionPixelSize(R.dimen.text_size_normal));
        mTvTitle.setText(title);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mEditKeyword.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mEditKeyword.setHint("请输入" + title);
        boolean isAutoSearch =
                typedArray.getBoolean(R.styleable.CustomSearchView_isAutoSearch, true);
        if (!isAutoSearch) {
            mEditKeyword.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            mEditKeyword.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    KeyboardUtils.hideSoftInput(v);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    if (onSearchListener != null) {
                        onSearchListener.search(v.getText().toString());
                    }
                    return true;
                }
                return false;

            });
        }
    }

    public void setOnEditorActionListener(OnSearchListener listener) {
        this.onSearchListener = listener;
    }

    public String getEditContent() {
        return mEditKeyword.getText().toString().trim();
    }

    public void addTextChangedListener(MyTextWatcher myTextWatcher) {
        mEditKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                                          int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                myTextWatcher.afterTextChanged(editable.toString());
            }
        });
    }

    public void setAutoCompleteList( List<String> autoCompleteList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.layout_item_auto_complete_text,
                autoCompleteList);
        mEditKeyword.setAdapter(adapter);
    }

    public void setContent(String s) {
        mEditKeyword.setText(s);
    }

    public interface MyTextWatcher {
        void afterTextChanged(String editContent);
    }

    public interface OnSearchListener {
        void search(String editContent);
    }
}
