package com.jinxb.developmentframework.domain.manager;


import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class HttpRequestManager {
    private final OkHttpClient.Builder builder;


    public static HttpRequestManager getInstance() {
        return HttpRequestManager.HttpRequestManagerHolder.sInstance;
    }

    private static class HttpRequestManagerHolder {
        private static final HttpRequestManager sInstance = new HttpRequestManager();
    }

    private HttpRequestManager() {
        builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

    public void requestGet(String url, AbsCallback callback) {
        requestGet(url, null, null, callback);
    }

    public void requestGet(String url, HashMap<String, String> params, AbsCallback callback) {
        requestGet(url, null, params, callback);
    }

    public void requestGet(String url, HttpHeaders httpHeaders, HashMap<String, String> params,
                           AbsCallback callback) {
        OkGo.<String>get(url)
                .retryCount(0)
                .headers(httpHeaders)
                .client(builder.build())
                .params(params).execute(callback);
    }

    public void requestPost(String url, HttpHeaders headers, HashMap<String, String> params, AbsCallback callback) {
        requestPost(url, headers, null, params, null, null, callback);
    }

    public void requestPost(String url, HashMap<String, String> params, AbsCallback callback) {
        requestPost(url, null, null, params, null, null, callback);
    }

    public void requestPost(String url, AbsCallback callback) {
        requestPost(url, null, null, null, null, null, callback);
    }

    public void requestPost(String url, HttpHeaders headers, AbsCallback callback) {
        requestPost(url, headers, null, null, null, null, callback);
    }

    public void requestPost(String url, RequestBody body, AbsCallback callback) {
        requestPost(url, null, body, null, null, null, callback);
    }

    public void requestPost(String url, HttpHeaders headers, RequestBody body, AbsCallback callback) {
        requestPost(url, headers, body, null, null, null, callback);
    }

    public void requestPost(String url, HashMap<String, String> params, String fileParamsKey, List<File> fileList,
                            AbsCallback callback) {
        requestPost(url, null, null, params, fileParamsKey, fileList, callback);
    }

    public void requestPost(String url, HttpHeaders headers, HashMap<String, String> params, String fileParamsKey,
                            List<File> fileList, AbsCallback callback) {
        requestPost(url, headers, null, params, fileParamsKey, fileList, callback);
    }

    public void requestPost(String url, HttpHeaders headers, RequestBody body, HashMap<String, String> params,
                            String fileParamsKey, List<File> fileList, AbsCallback callback) {
        PostRequest<String> request = OkGo.post(url);
        if (!TextUtils.isEmpty(fileParamsKey)) {
            request.isMultipart(true);
        }
        request.retryCount(0)
                .headers(headers)
                .client(builder.build())
                .upRequestBody(body)
                .params(params)
                .addFileParams(fileParamsKey, fileList)
                .execute(callback);
    }
}
