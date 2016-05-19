package com.wings.v2ex;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.wings.v2ex.util.RetrofitDemo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testRetrofit() {
        RetrofitDemo.getAsync();
    }
}