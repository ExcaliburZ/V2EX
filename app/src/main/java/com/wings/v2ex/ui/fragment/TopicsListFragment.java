package com.wings.v2ex.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wings.v2ex.GlobalConstant;
import com.wings.v2ex.R;
import com.wings.v2ex.TopicsContract;
import com.wings.v2ex.model.domain.Topic;
import com.wings.v2ex.presenter.TopicPresenter;

import java.util.List;

/**
 * A fragment representing a list of Topics,use Tag to discriminate different tabs
 * and show tabs.
 * .
 */
public class TopicsListFragment extends Fragment implements TopicsContract.View {

    private int id;
    private int mColumnCount = 1;
    private RecyclerView mRecyclerView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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
//            recyclerView.setAdapter(new TopicsListRecyclerViewAdapter());
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
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = View.inflate(
                        TopicsListFragment.this.getContext(), R.layout.item_tips, null);
                TipViewHolder viewHolder = new TipViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                TipViewHolder tipViewHolder = (TipViewHolder) holder;
                tipViewHolder.title.setText(TopicList.get(position).getTitle());
                tipViewHolder.replies.setText(String.valueOf(TopicList.get(position).getReplies()));
            }

            @Override
            public int getItemCount() {
                return TopicList.size();
            }
        });
    }

    class TipViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView replies;

        public TipViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            replies = (TextView) itemView.findViewById(R.id.tv_replies);
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
