package com.chanchuan.adpater;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chanchuan.base.BaseAdapter;
import com.chanchuan.bean.SectionsBean;
import com.chanchuan.geeknews.R;

public class SectionAdapter extends BaseAdapter<SectionsBean.DataBean> {
    public SectionAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.section_item;
    }

    @Override
    protected void bindData(BaseAdapter.BaseViewHolder baseViewHolder, SectionsBean.DataBean dataBean) {
        ImageView img_section = (ImageView) baseViewHolder.getViewById(R.id.img_section);
        TextView name_section = (TextView) baseViewHolder.getViewById(R.id.name_section);
        Glide.with(context).load(dataBean.getThumbnail()).into(img_section);
        name_section.setText(dataBean.getName());
    }
}

