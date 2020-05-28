package com.chanchuan.presenter;

import com.chanchuan.Utils.RxUtils;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.bean.CartBean;
import com.chanchuan.contract.WeChatContract;
import com.chanchuan.net.HttpManager;
import com.chanchuan.serivce.ApiService;

import io.reactivex.observers.ResourceObserver;

public class WeChatPresenter extends BasePresenter<WeChatContract.View> implements WeChatContract.Presenter {
    @Override
    public void getCartData() {
        ResourceObserver<CartBean> resourceObserver = HttpManager.getInstance()
                .getService(ApiService.cartUrl, ApiService.class)
                .getCartListData()
                .compose(RxUtils.<CartBean>rxScheduler())
                .subscribeWith(new ResourceObserver<CartBean>() {
                    @Override
                    public void onNext(CartBean cartBean) {
                        view.successData(cartBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(resourceObserver);
    }
}
