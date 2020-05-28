package com.chanchuan.geeknews;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chanchuan.Utils.HtmlUtil;
import com.chanchuan.base.BaseActivity;
import com.chanchuan.bean.ZhihuDetailBean;
import com.chanchuan.contract.ZhihuContract;
import com.chanchuan.eventbus.DetailEvent;
import com.chanchuan.presenter.ZhihuPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity<ZhihuPresenter> implements ZhihuContract.View {
    int id;
    @BindView(R.id.detail_top_img)
    ImageView detailTopImg;
    @BindView(R.id.detail_toolBar)
    Toolbar detailToolBar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected ZhihuPresenter initPresenter() {
        return new ZhihuPresenter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getId(DetailEvent detailEvent) {
        id = detailEvent.getId();
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getDetailsData(id);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detaily;
    }

    @Override
    public void successData(Object o) {
        if (o instanceof ZhihuDetailBean) {
            ZhihuDetailBean zhihuDetailBean = (ZhihuDetailBean) o;
            Glide.with(this).load(zhihuDetailBean.getImage()).into(detailTopImg);
            detailToolBar.setTitle(zhihuDetailBean.getTitle());
            setSupportActionBar(detailToolBar);
            String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.getBody(), zhihuDetailBean.getCss(), (List<String>) zhihuDetailBean.getJs());
            webView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);

        }
    }

    @Override
    public void beforeSuccessData(Object o) {

    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
