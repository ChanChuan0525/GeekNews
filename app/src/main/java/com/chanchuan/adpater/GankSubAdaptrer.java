package com.chanchuan.adpater;

import android.content.Context;
import android.widget.TextView;

import com.chanchuan.base.BaseAdapter;
import com.chanchuan.bean.GankBean;
import com.chanchuan.geeknews.R;

public class GankSubAdaptrer extends BaseAdapter<GankBean.ResultsBean> {
    public GankSubAdaptrer(Context context) {
        super(context);
    }


    @Override
    protected int getItemLayout() {
        return R.layout.sub_gank_item;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, GankBean.ResultsBean resultsBean) {
        TextView textView = (TextView) baseViewHolder.getViewById(R.id.android_name_item);
        textView.setText(resultsBean.getDesc());
    }
}
