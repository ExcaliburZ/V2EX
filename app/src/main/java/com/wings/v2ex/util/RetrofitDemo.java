package com.wings.v2ex.util;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wing on 2016/5/19.
 */
public class RetrofitDemo {
    private static final String TAG = "RetrofitDemo";

    public static void getAsync() {
        //        URL: http://www.v2ex.com/api/topics/latest.json
        //        返回值
        //        id - Topic ID
        //        title - Topic 标题
        //        content - Topic 内容
        //        content_rendered - 渲染过的 Topic 内容（比如 gist, Youtube 等）
        //        replies - 回复数量
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.v2ex.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ITopTopicsBiz iTopTopicsBiz = retrofit.create(ITopTopicsBiz.class);
        Call<List<Topic>> topTopics = iTopTopicsBiz.getTopTopics();
        topTopics.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                List<Topic> body = response.body();
                Log.i(TAG, "onResponse: " + body.size());
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {

            }
        });

    }
}
