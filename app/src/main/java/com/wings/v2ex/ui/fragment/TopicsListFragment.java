package com.wings.v2ex.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wings.v2ex.GlobalConstant;
import com.wings.v2ex.R;
import com.wings.v2ex.TopicsContract;
import com.wings.v2ex.databinding.ItemListTopicBinding;
import com.wings.v2ex.model.domain.Topic;
import com.wings.v2ex.presenter.TopicPresenter;

import java.util.List;

/**
 * A fragment representing a list of Topics,use Tag to discriminate different tabs
 * and show tabs.
 * .
 */
public class TopicsListFragment extends Fragment implements TopicsContract.View {

    private static final String TAG = "TopicsListFragment";
    private int id;
    private int mColumnCount = 1;
    private RecyclerView mRecyclerView;
    private List<Topic> mTopicList;

    public TopicsListFragment() {
    }

    public Fragment setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }
        initData();
        return view;
    }

    private void initData() {
        TopicPresenter topicPresenter = new TopicPresenter(this);
        switch (id) {
            case GlobalConstant.GET_HOT_TOPICS:
                topicPresenter.showHotTopicList();
                break;
            case GlobalConstant.GET_LATEST_TOPICS:
                topicPresenter.showLatestTopicList();
                break;

        }
    }

    @Override
    public void showTopics(final List<Topic> TopicList) {
        mTopicList = TopicList;
        QuickAdapter adapter = new QuickAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Log.i(TAG, "onItemClick: position ::"
                        + i + "\nitem.title ::"
                        + mTopicList.get(i).getTitle());
            }
        });
    }

    public class QuickAdapter extends BaseQuickAdapter<Topic> {
        public QuickAdapter() {
            super(R.layout.item_list_topic, mTopicList);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Topic item) {
            ItemListTopicBinding bind = DataBindingUtil.bind(helper.itemView);
            bind.setTopic(item);
//            Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
        }
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
}