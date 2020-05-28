package com.chanchuan.fragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chanchuan.adpater.DailyAdapter;
import com.chanchuan.base.BaseFragment;
import com.chanchuan.bean.DailyListBean;
import com.chanchuan.contract.ZhihuContract;
import com.chanchuan.eventbus.CalendarEvent;
import com.chanchuan.geeknews.CalendarActivity;
import com.chanchuan.geeknews.R;
import com.chanchuan.presenter.ZhihuPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class DailyFragment extends BaseFragment<ZhihuPresenter> implements ZhihuContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.button)
    FloatingActionButton button;
    private DailyAdapter dailyAdapter;
    private String date;

    @Override
    protected int getLayout() {
        return R.layout.fragment_daily;
    }

    private static final String TAG = "DailyFragment";

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getDailyData();
    }

    @Override
    protected void initView(View root) {
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        dailyAdapter = new DailyAdapter(context);
        recycler.setAdapter(dailyAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CalendarActivity.class));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiverData(CalendarEvent calendarEvent) {
        date = calendarEvent.getDate();
        Log.d(TAG, "receiverData: " + date);
        presenter.getBeforeData(date);
    }

    @Override
    protected ZhihuPresenter initPresenter() {
        return new ZhihuPresenter();
    }

    @Override
    public void successData(Object o) {
        if (o instanceof DailyListBean) {
            DailyListBean dailyListBean = (DailyListBean) o;
            List<DailyListBean.StoriesBean> stories = dailyListBean.getStories();
            List<DailyListBean.TopStoriesBean> top_stories = dailyListBean.getTop_stories();
            dailyAdapter.setDate("今日新闻");
            dailyAdapter.setStoriesBeans(stories);
            dailyAdapter.setTopStoriesBeans(top_stories);
        }
    }

    @Override
    public void beforeSuccessData(Object o) {
        if (o instanceof DailyListBean) {
            DailyListBean dailyListBean = (DailyListBean) o;
            List<DailyListBean.StoriesBean> stories = dailyListBean.getStories();
            dailyAdapter.setDate(date);
            dailyAdapter.setBeforeStoriesBeans(stories);
        }
    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
