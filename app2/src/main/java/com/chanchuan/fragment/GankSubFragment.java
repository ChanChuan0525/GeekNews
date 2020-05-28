package com.chanchuan.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chanchuan.Utils.ScreenUtils;
import com.chanchuan.adpater.GankSubAdaptrer;
import com.chanchuan.base.BaseFragment;
import com.chanchuan.bean.GankBean;
import com.chanchuan.contract.GankContract;
import com.chanchuan.geeknews.R;
import com.chanchuan.presenter.GankPresenter;

import java.util.List;

import butterknife.BindView;

public class GankSubFragment extends BaseFragment<GankPresenter> implements GankContract.View {
    @BindView(R.id.sub_img)
    ImageView subImg;
    @BindView(R.id.sub_blue)
    ImageView subBlue;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.sub_recycler)
    RecyclerView subRecycler;
    private GankSubAdaptrer subAdaptrer;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_sub;
    }

    @Override
    protected void initData() {
        presenter.getGankListData("Android", "20");
    }

    @Override
    protected void initView(View root) {
        Glide.with(context).load("http://gank.io/images/1d47d22e27884f7cac2a7e88a38993bf").into(subImg);
        subBlue.setAlpha(0f);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int size = ScreenUtils.dip2px(getActivity(), 256f);
                float percent = Math.abs(i) / size;
                subBlue.setAlpha(percent);
            }
        });

        subRecycler.setLayoutManager(new LinearLayoutManager(context));
        subRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        subAdaptrer = new GankSubAdaptrer(context);
        subRecycler.setAdapter(subAdaptrer);
    }
    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    public void successData(Object o) {
        if (o instanceof GankBean) {
            GankBean gankBean = (GankBean) o;
            List<GankBean.ResultsBean> results = gankBean.getResults();
            subAdaptrer.addDataList(results);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

}
