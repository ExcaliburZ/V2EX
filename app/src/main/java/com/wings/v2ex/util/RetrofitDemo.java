package com.wings.v2ex.util;

import android.util.Log;

import com.wings.v2ex.model.domain.Reply;
import com.wings.v2ex.model.domain.Topic;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wing on 2016/5/19.
 */
public class RetrofitDemo {
    private final static String BASE_URL = "http://www.v2ex.com/api/";
    private final static String TAG = "RetrofitDemo";
    private static RetrofitDemo instance = new RetrofitDemo();
    private static ITopicsBiz mITopTopicsBiz;
    private final Retrofit mRetrofit;

    public static RetrofitDemo getInstance() {
        return instance;
    }

    private RetrofitDemo() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mITopTopicsBiz = mRetrofit.create(ITopicsBiz.class);
    }

    public void getLatestTopics(Subscriber<List<Topic>> subscriber) {
        mITopTopicsBiz.getLatestTopicsRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getHotTopics(Subscriber<List<Topic>> subscriber) {
        mITopTopicsBiz.getHotTopicsRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public List<Topic> getLatestTopics() {
        Call<List<Topic>> topTopics = mITopTopicsBiz.getLatestTopics();
        try {
            return topTopics.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "getAsync: error");
        }
        return null;
    }

    public void getLatestTopicsRxDemo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ITopicsBiz iTopicsBiz = retrofit.create(ITopicsBiz.class);
        iTopicsBiz.getLatestTopicsRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Topic>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: error");
                    }

                    @Override
                    public void onNext(List<Topic> topics) {
                        Log.i(TAG, "onNext: topics" + topics.size());
                    }
                });
    }

    public static void getRepliesByIdDemo(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.v2ex.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //动态代理创建ITopicsBiz对象
        ITopicsBiz iTopTopicsBiz = retrofit.create(ITopicsBiz.class);
        //Invoke把方法解析成Request,在构造一个OkHttpCall
        Call<List<Reply>> topicRepliesById = iTopTopicsBiz.getTopicRepliesById(id);
        topicRepliesById.enqueue(new Callback<List<Reply>>() {
            @Override
            public void onResponse(Call<List<Reply>> call, Response<List<Reply>> response) {
                List<Reply> replies = response.body();
                Log.i(TAG, "onResponse: " + replies.size());
            }

            @Override
            public void onFailure(Call<List<Reply>> call, Throwable t) {
                Log.i(TAG, "onFailure: fail!!!!!!!!!!");
            }
        });
    }
}
