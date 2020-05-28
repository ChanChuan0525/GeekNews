package com.chanchuan.adpater;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chanchuan.base.BaseAdapter;
import com.chanchuan.bean.CartBean;
import com.chanchuan.eventbus.CartEvent;
import com.chanchuan.geeknews.R;

import org.greenrobot.eventbus.EventBus;

public class CartAdapter extends BaseAdapter<CartBean.DataBean.CartListBean> {
    public CartAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.cart_item;
    }


    @Override
    protected void bindData(BaseAdapter.BaseViewHolder baseViewHolder, final CartBean.DataBean.CartListBean cartListBean) {
        final CheckBox checkBox = (CheckBox) baseViewHolder.getViewById(R.id.checkBox);
        ImageView img_item = (ImageView) baseViewHolder.getViewById(R.id.img_item);
        TextView name_item = (TextView) baseViewHolder.getViewById(R.id.name_item);
        TextView price_item = (TextView) baseViewHolder.getViewById(R.id.price_item);
        TextView number_item = (TextView) baseViewHolder.getViewById(R.id.number_item);

        Glide.with(context).load(cartListBean.getList_pic_url()).into(img_item);
        name_item.setText(cartListBean.getGoods_name());
        price_item.setText(cartListBean.getRetail_price() + "");
        number_item.setText(cartListBean.getNumber() + "");

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cartListBean.setCheck(isChecked);
                computePrice();
            }
        });
        checkBox.setChecked(cartListBean.isCheck());
    }

    private void computePrice() {
        int price = 0;
        int number = 0;
        for (int i = 0; i < dataList.size(); i++) {
            CartBean.DataBean.CartListBean cartListBean = dataList.get(i);
            if (cartListBean.isCheck()) {
                price += cartListBean.getNumber() * cartListBean.getRetail_price();
                number += cartListBean.getNumber();
            }
        }
        EventBus.getDefault().post(new CartEvent(price, number));
    }
}
