package com.chanchuan.fragment;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chanchuan.adpater.CartAdapter;
import com.chanchuan.base.BaseFragment;
import com.chanchuan.bean.CartBean;
import com.chanchuan.contract.WeChatContract;
import com.chanchuan.eventbus.CartEvent;
import com.chanchuan.geeknews.R;
import com.chanchuan.presenter.WeChatPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeChatFragment extends BaseFragment<WeChatPresenter> implements WeChatContract.View {

    @BindView(R.id.recycler_wechat)
    RecyclerView recyclerWechat;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.total_price)
    TextView totalPrice;
    private CartAdapter cartAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_we_chat;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getCartData();
    }

    @Override
    protected void initView(View root) {
        recyclerWechat.setLayoutManager(new LinearLayoutManager(context));
        recyclerWechat.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        cartAdapter = new CartAdapter(context);
        recyclerWechat.setAdapter(cartAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPrice(CartEvent cartEvent) {
        totalPrice.setText("总价:" + cartEvent.getPrice() + "元");
        count.setText("全部:(" + cartEvent.getNumber() + ")");
    }

    @Override
    protected WeChatPresenter initPresenter() {
        return new WeChatPresenter();
    }

    @Override
    public void successData(Object o) {
        if (o instanceof CartBean) {
            CartBean cartBean = (CartBean) o;
            List<CartBean.DataBean.CartListBean> cartList = cartBean.getData().getCartList();
            cartAdapter.addDataList(cartList);
        }
    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
