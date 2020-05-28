package com.chanchuan.contract;

import com.chanchuan.base.IBasePresenter;
import com.chanchuan.base.IBaseView;

public interface ZhihuContract {
    interface View<V> extends IBaseView {
        void successData(V v);

        void beforeSuccessData(V v);
    }

    interface Presenter extends IBasePresenter<View> {
        void getDailyData();

        void getBeforeData(String date);

        void getSectionsData();

        void getDetailsData(int id);
    }
}
