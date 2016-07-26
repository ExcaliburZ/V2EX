package com.wings.v2ex.model;

import com.wings.v2ex.model.domain.Reply;
import com.wings.v2ex.model.domain.Topic;
import com.wings.v2ex.util.RetrofitManager;

import java.util.List;

import rx.Subscriber;

/**
 * Created by wing on 2016/5/19.
 */
public class TopicModel implements ITopicModel {
    private RetrofitManager mRetrofitDemo = RetrofitManager.getInstance();

    @Override
    public void getLatestTopics(Subscriber<List<Topic>> subscriber) {
        //TODO can do disk cache here.
        mRetrofitDemo.getLatestTopics(subscriber);
    }

    @Override
    public void getHotTopics(Subscriber<List<Topic>> subscriber) {
        mRetrofitDemo.getHotTopics(subscriber);
    }

    @Override
    public List<Reply> getRepliesById(int id) {
        return null;
    }
}
