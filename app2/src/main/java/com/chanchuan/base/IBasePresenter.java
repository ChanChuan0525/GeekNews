package com.chanchuan.base;

public interface IBasePresenter<V> {
    void attach(V view);

    void detach();
}
