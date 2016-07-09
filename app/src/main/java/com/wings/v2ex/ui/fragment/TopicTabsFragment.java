package com.wings.v2ex.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wings.v2ex.GlobalConstant;
import com.wings.v2ex.R;
import com.wings.v2ex.model.domain.Tab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainContent include a lot of Tabs such as Latest tab,Hotest tab.
 */
public class TopicTabsFragment extends Fragment {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    public TopicTabsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mRootView = inflater.inflate(R.layout.fragment_topics_list, container, false);
        ButterKnife.bind(this, mRootView);
        FragmentPagerAdapter adapter = getAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin));

        ViewCompat.setElevation(mTabLayout, getResources().getDimension(R.dimen.appbar_elevation));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return mRootView;
    }

    private FragmentPagerAdapter getAdapter(FragmentManager fragmentManager) {
        return new CategoryFragmentAdapter(fragmentManager);
    }

    private class CategoryFragmentAdapter extends FragmentPagerAdapter {
        private final List<Tab> mTabs = new ArrayList() {{
            add(new Tab(GlobalConstant.GET_HOT_TOPICS, getString(R.string.hot)));
            add(new Tab(GlobalConstant.GET_LATEST_TOPICS, getString(R.string.latest)));
        }};

        public CategoryFragmentAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return new TopicsListFragment().setId(mTabs.get(position).getId());
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getName();
        }
    }

}
