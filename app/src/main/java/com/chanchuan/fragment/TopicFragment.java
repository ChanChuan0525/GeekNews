package com.chanchuan.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chanchuan.adpater.SectionAdapter;
import com.chanchuan.base.BaseFragment;
import com.chanchuan.bean.SectionsBean;
import com.chanchuan.contract.ZhihuContract;
import com.chanchuan.geeknews.R;
import com.chanchuan.presenter.ZhihuPresenter;

import java.util.List;

import butterknife.BindView;

public class TopicFragment extends BaseFragment<ZhihuPresenter> implements ZhihuContract.View {
    @BindView(R.id.recycler_topic)
    RecyclerView recycler_topic;
    private SectionAdapter sectionAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initData() {
        presenter.getSectionsData();
    }

    @Override
    protected void initView(View root) {
        recycler_topic.setLayoutManager(new GridLayoutManager(context, 2));
        sectionAdapter = new SectionAdapter(context);
        recycler_topic.setAdapter(sectionAdapter);

    }

    @Override
    protected ZhihuPresenter initPresenter() {
        return new ZhihuPresenter();
    }

    @Override
    public void successData(Object o) {
        if (o instanceof SectionsBean) {
            SectionsBean sectionsBean = (SectionsBean) o;
            List<SectionsBean.DataBean> data = sectionsBean.getData();
            sectionAdapter.addDataList(data);
        }
    }

    @Override
    public void beforeSuccessData(Object o) {

    }

    @Override
    public void error(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }
}
