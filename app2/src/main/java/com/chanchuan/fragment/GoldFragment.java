package com.chanchuan.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanchuan.base.BaseFragment;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.geeknews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
