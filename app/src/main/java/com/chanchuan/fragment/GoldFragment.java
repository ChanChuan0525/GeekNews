package com.chanchuan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chanchuan.Utils.Constants;
import com.chanchuan.base.BaseFragment;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.eventbus.TabEvent;
import com.chanchuan.geeknews.GoldManagerActivity;
import com.chanchuan.geeknews.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment {

    @BindView(R.id.gold_tabLayout)
    TabLayout goldTabLayout;
    @BindView(R.id.gold_viewPager)
    ViewPager goldViewPager;
    @BindView(R.id.gold_menu)
    ImageView goldMenu;
    public static String[] typeStr = {"Android", "IOS", "前端", "后端", "设计", "产品", "阅读", "工具资源"};
    //    public static String[] type = {"android", "ios", "frontend", "backend", "design", "product", "article", "freebie"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gold;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateTabUI(TabEvent tabEvent) {
        goldTabLayout.removeAllTabs();

        for (int i = 0; i < Constants.isSelected.size(); i++) {
            if (Constants.isSelected.get(i)) {
                goldTabLayout.addTab(goldTabLayout.newTab().setText(Constants.title.get(i)));
            }
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View root) {
        for (int i = 0; i < typeStr.length; i++) {
            Constants.title.add(typeStr[i]);
            if (i % 2 == 0) {
                Constants.isSelected.add(true);
            } else {
                Constants.isSelected.add(false);
            }
        }
        for (int i = 0; i < Constants.isSelected.size(); i++) {
            if (Constants.isSelected.get(i)) {
                goldTabLayout.addTab(goldTabLayout.newTab().setText(Constants.title.get(i)));
            }
        }
        goldMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, GoldManagerActivity.class));
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
