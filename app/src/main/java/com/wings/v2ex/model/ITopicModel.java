package com.wings.v2ex.model;

import com.wings.v2ex.model.domain.Reply;
import com.wings.v2ex.model.domain.Topic;

import java.util.List;

import rx.Subscriber;

/**
 * Created by wing on 2016/5/19.
 */
public interface ITopicModel {
    void getLatestTopics(Subscriber<List<Topic>> subscriber);

    void getHotTopics(Subscriber<List<Topic>> subscriber);

    List<Reply> getRepliesById(int id);
}
