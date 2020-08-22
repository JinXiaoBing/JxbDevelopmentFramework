/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jinxb.developmentframework.data.convert;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Create by KunMinX at 19/7/13
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        T body = response.body();
        onSuccess(body);
    }

    public abstract void onSuccess(T body);

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public T convertResponse(Response response) {
        if (response.isSuccessful()) {
            if (response.body() == null) {
                return null;
            }
            T data = null;
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(response.body().charStream());
            Type genType = getClass().getGenericSuperclass();
            assert genType != null;
            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            data = gson.fromJson(jsonReader, type);
            return data;
        }
        return null;
    }
}
