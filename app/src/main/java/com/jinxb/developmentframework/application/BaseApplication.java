package com.jinxb.developmentframework.application;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.jinxb.developmentframework.view_model.SharedViewModel;

public class BaseApplication extends Application implements ViewModelStoreOwner {
    private static BaseApplication mInstance;
    private SharedViewModel mSharedViewModel;
    private ViewModelStore mAppViewModelStore;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppViewModelStore = new ViewModelStore();

    }

    public static Object getInstance() {
        return mInstance;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
    public SharedViewModel getSharedViewModel() {
        if (mSharedViewModel == null) {
            mSharedViewModel =
                    ViewModelProvider.AndroidViewModelFactory.getInstance(this).create(SharedViewModel.class);
        }
        return mSharedViewModel;
    }
}
