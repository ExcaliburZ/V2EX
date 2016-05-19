package com.wings.v2ex.util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wing on 2016/5/19.
 */
public interface ITopTopicsBiz {
    @GET("topics/latest.json")
    Call<List<Topic>> getTopTopics();
}
