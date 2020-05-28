package com.chanchuan.adpater;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chanchuan.base.BaseAdapter;
import com.chanchuan.bean.GirlBean;
import com.chanchuan.geeknews.R;

import java.util.Random;

public class GirlAdapter extends BaseAdapter<GirlBean.ResultsBean> {
    public GirlAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.girl_item;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, GirlBean.ResultsBean resultsBean) {
        ImageView girl_item_img = (ImageView) baseViewHolder.getViewById(R.id.girl_item_img);
        Glide.with(context).load(resultsBean.getUrl()).into(girl_item_img);

        Random random = new Random();
        int i = random.nextInt(600);
        int height = i < 300 ? 300 : i;
        ViewGroup.LayoutParams layoutParams = girl_item_img.getLayoutParams();
        layoutParams.height = height;
        girl_item_img.setLayoutParams(layoutParams);
    }
}
