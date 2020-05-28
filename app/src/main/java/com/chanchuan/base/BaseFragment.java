package com.chanchuan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private Unbinder bind;
    protected P presenter;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayout(), container, false);
        context = getActivity();
        bind = ButterKnife.bind(this, root);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attach(this);
        }
        initView(root);
        initData();
        return root;
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView(View root);

    protected abstract P initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
