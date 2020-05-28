package com.chanchuan.presenter;

import com.chanchuan.Utils.RxUtils;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.bean.DailyListBean;
import com.chanchuan.bean.SectionsBean;
import com.chanchuan.bean.ZhihuDetailBean;
import com.chanchuan.contract.ZhihuContract;
import com.chanchuan.net.HttpManager;
import com.chanchuan.serivce.ApiService;

import io.reactivex.observers.ResourceObserver;

public class ZhihuPresenter<V> extends BasePresenter<ZhihuContract.View> implements ZhihuContract.Presenter {
    @Override
    public void getDailyData() {
        ResourceObserver<DailyListBean> daily = HttpManager.getInstance()
                .getService(ApiService.zhidhuUrl, ApiService.class)
                .getDailayData()
                .compose(RxUtils.<DailyListBean>rxScheduler())
                .subscribeWith(new ResourceObserver<DailyListBean>() {
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        view.successData(dailyListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(daily);

    }

    @Override
    public void getBeforeData(String date) {

        ResourceObserver<DailyListBean> daily = HttpManager.getInstance()
                .getService(ApiService.zhidhuUrl, ApiService.class)
                .getBeforeData(date)
                .compose(RxUtils.<DailyListBean>rxScheduler())
                .subscribeWith(new ResourceObserver<DailyListBean>() {
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        view.beforeSuccessData(dailyListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(daily);
    }

    @Override
    public void getSectionsData() {

        ResourceObserver<SectionsBean> sections = HttpManager.getInstance()
                .getService(ApiService.zhidhuUrl, ApiService.class)
                .getSections()
                .compose(RxUtils.<SectionsBean>rxScheduler())
                .subscribeWith(new ResourceObserver<SectionsBean>() {
                    @Override
                    public void onNext(SectionsBean sectionsBean) {
                        view.successData(sectionsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(sections);
    }

    @Override
    public void getDetailsData(int id) {
        ResourceObserver<ZhihuDetailBean> detailBeanResourceObserver = HttpManager.getInstance()
                .getService(ApiService.zhidhuUrl, ApiService.class)
                .getZhihuDetail(id)
                .compose(RxUtils.<ZhihuDetailBean>rxScheduler())
                .subscribeWith(new ResourceObserver<ZhihuDetailBean>() {
                    @Override
                    public void onNext(ZhihuDetailBean zhihuDetailBean) {
                        view.successData(zhihuDetailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(detailBeanResourceObserver);
    }
}
