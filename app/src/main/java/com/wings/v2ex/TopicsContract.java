package com.wings.v2ex;

import com.wings.v2ex.model.domain.Topic;

import java.util.List;

/**
 * Created by wing on 2016/5/6.
 */
public interface TopicsContract {

    interface Presenter {
        void showLatestTopicList();

        void showHotTopicList();

        void refreshTopics();

    }

    interface View {

        void showTopics(List<Topic> TopicList);

        void refreshTopics(List<Topic> TopicList);

        void showLoading();

        void cancelLoading();
    }
}
