package com.chanchuan.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanchuan.base.BaseFragment;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.geeknews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initData() {
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("日报");
        tabLayout.getTabAt(1).setText("专栏");
        tabLayout.getTabAt(2).setText("热门");
    }

    @Override
    protected void initView(View root) {
        fragments.add(new DailyFragment());
        fragments.add(new TopicFragment());
        fragments.add(new DailyFragment());

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
