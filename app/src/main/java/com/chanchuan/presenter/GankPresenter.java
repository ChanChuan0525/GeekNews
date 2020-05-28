package com.chanchuan.presenter;

import com.chanchuan.Utils.RxUtils;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.bean.GankBean;
import com.chanchuan.bean.GirlBean;
import com.chanchuan.contract.GankContract;
import com.chanchuan.net.HttpManager;
import com.chanchuan.serivce.ApiService;

import io.reactivex.observers.ResourceObserver;

public class GankPresenter extends BasePresenter<GankContract.View> implements GankContract.Presenter {

    ApiService service = HttpManager.getInstance().getService(ApiService.gankUrl, ApiService.class);

    @Override
    public void getGankListData(String type, String page) {
        ResourceObserver<GankBean> android = service.getGankData(type, page)
                .compose(RxUtils.<GankBean>rxScheduler())
                .subscribeWith(new ResourceObserver<GankBean>() {
                    @Override
                    public void onNext(GankBean gankBean) {
                        view.successData(gankBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(android);
    }

    @Override
    public void getGirlListData() {
        ResourceObserver<GirlBean> girlBeanResourceObserver = service.getGirlData()
                .compose(RxUtils.<GirlBean>rxScheduler())
                .subscribeWith(new ResourceObserver<GirlBean>() {
                    @Override
                    public void onNext(GirlBean bean) {
                        view.successData(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(girlBeanResourceObserver);
    }
}
