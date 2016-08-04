package com.wings.v2ex.presenter;

import android.util.Log;

import com.wings.v2ex.TopicsContract;
import com.wings.v2ex.model.ITopicModel;
import com.wings.v2ex.model.TopicModel;
import com.wings.v2ex.model.domain.Topic;

import java.util.List;

import rx.Subscriber;

/**
 * Created by wing on 2016/5/19.
 */
public class TopicPresenter implements TopicsContract.Presenter {
    private static final String TAG = "TopicPresenter";
    private ITopicModel mITopicModel;
    private TopicsContract.View mView;

    public TopicPresenter(TopicsContract.View view) {
        mView = view;
        mITopicModel = new TopicModel();
    }

    @Override
    public void showLatestTopicList() {
        mITopicModel.getLatestTopics(new Subscriber<List<Topic>>() {
            @Override
            public void onNext(List<Topic> topics) {
                mView.showTopics(topics);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    @Override
    public void showHotTopicList() {
        mITopicModel.getHotTopics(new Subscriber<List<Topic>>() {
            @Override
            public void onNext(List<Topic> topics) {
                mView.showTopics(topics);
            }

            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
            }
        });
    }

    @Override
    public void refreshTopics() {

    }
}
