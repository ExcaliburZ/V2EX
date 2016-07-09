package com.wings.v2ex;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.wings.v2ex.model.domain.Topic;
import com.wings.v2ex.presenter.TopicPresenter;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testRetrofit() {
        TopicPresenter presenter = new TopicPresenter(new TopicsContract.View() {
            @Override
            public void showTopics(List<Topic> TopicList) {
                System.out.println(TopicList);
                System.out.println("");
            }

            @Override
            public void refreshTopics(List<Topic> TopicList) {

            }

            @Override
            public void showLoading() {

            }

            @Override
            public void cancelLoading() {

            }
        });
        presenter.showHotTopicList();
    }
}