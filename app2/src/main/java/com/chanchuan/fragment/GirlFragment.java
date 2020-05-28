package com.chanchuan.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chanchuan.adpater.GirlAdapter;
import com.chanchuan.base.BaseFragment;
import com.chanchuan.bean.GirlBean;
import com.chanchuan.contract.GankContract;
import com.chanchuan.geeknews.R;
import com.chanchuan.presenter.GankPresenter;

import java.util.List;

import butterknife.BindView;

public class GirlFragment extends BaseFragment<GankPresenter> implements GankContract.View {
    @BindView(R.id.recycler_girl)
    RecyclerView girlRecycler;
    private GirlAdapter girlAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_girl;
    }

    @Override
    protected void initData() {
        presenter.getGirlListData();
    }

    @Override
    protected void initView(View root) {
        girlRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        girlAdapter = new GirlAdapter(context);
        girlRecycler.setAdapter(girlAdapter);
    }

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    public void successData(Object o) {
        if (o instanceof GirlBean) {
            GirlBean girlBean = (GirlBean) o;
            List<GirlBean.ResultsBean> results = girlBean.getResults();
            girlAdapter.addDataList(results);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }
}
