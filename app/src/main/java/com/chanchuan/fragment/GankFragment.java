package com.chanchuan.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chanchuan.base.BaseFragment;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.geeknews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GankFragment extends BaseFragment {

    @BindView(R.id.gank_tabLayout)
    TabLayout gankTabLayout;
    @BindView(R.id.gank_viewPager)
    ViewPager gankViewPager;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View root) {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GankSubFragment());
        fragments.add(new GirlFragment());
        gankViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        gankTabLayout.setupWithViewPager(gankViewPager);
        gankTabLayout.getTabAt(0).setText("Android");
        gankTabLayout.getTabAt(1).setText("福利");
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


}
