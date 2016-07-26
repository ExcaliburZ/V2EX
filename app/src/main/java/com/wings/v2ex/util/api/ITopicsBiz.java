package com.wings.v2ex.util.api;

import com.wings.v2ex.model.domain.Reply;
import com.wings.v2ex.model.domain.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wing on 2016/5/19.
 */
public interface ITopicsBiz {
    @GET("topics/latest.json")
    Call<List<Topic>> getLatestTopics();

    @GET("topics/latest.json")
    Observable<List<Topic>> getLatestTopicsRx();

    @GET("topics/hot.json")
    Observable<List<Topic>> getHotTopicsRx();

    @GET("replies/show.json")
    Call<List<Reply>> getTopicRepliesById(@Query("topic_id") int id);
}
