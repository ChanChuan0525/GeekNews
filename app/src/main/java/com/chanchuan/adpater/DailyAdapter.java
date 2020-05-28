package com.chanchuan.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chanchuan.bean.DailyListBean;
import com.chanchuan.eventbus.DetailEvent;
import com.chanchuan.geeknews.DetailActivity;
import com.chanchuan.geeknews.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyAdapter extends RecyclerView.Adapter {
    Context context;
    private final static int BANNER_TYPE = 0;
    private final static int OTHER_TYPE = 1;
    private final static int DATE_TYPE = 2;
    String date;
    List<DailyListBean.StoriesBean> storiesBeans = new ArrayList<>();
    List<DailyListBean.TopStoriesBean> topStoriesBeans = new ArrayList<>();
    List<String> bannerTitle = new ArrayList<>();

    public void setDate(String date) {
        this.date = date;
    }

    public DailyAdapter(Context context) {
        this.context = context;
    }

    public void setStoriesBeans(List<DailyListBean.StoriesBean> storiesBeans) {
        this.storiesBeans.addAll(storiesBeans);
        notifyDataSetChanged();
    }

    public void setBeforeStoriesBeans(List<DailyListBean.StoriesBean> storiesBeans) {
        this.topStoriesBeans.clear();
        this.storiesBeans.clear();
        this.storiesBeans.addAll(storiesBeans);
        notifyDataSetChanged();
    }

    public void setTopStoriesBeans(List<DailyListBean.TopStoriesBean> topStoriesBeans) {
        this.topStoriesBeans.addAll(topStoriesBeans);
        notifyDataSetChanged();
        if (topStoriesBeans.size() != 0 && bannerTitle.size() == 0) {
            for (DailyListBean.TopStoriesBean topStoriesBean : topStoriesBeans) {
                bannerTitle.add(topStoriesBean.getTitle());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && topStoriesBeans.size() != 0) {
            return BANNER_TYPE;
        } else if ((topStoriesBeans.size() == 0 && position == 0) || (topStoriesBeans.size() > 0 && !TextUtils.isEmpty(date) && position == 1)) {
            return DATE_TYPE;
        } else {
            return OTHER_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case BANNER_TYPE:
                View root = LayoutInflater.from(context).inflate(R.layout.banner_layout, viewGroup, false);
                return new BannerViewHolder(root);
            case DATE_TYPE:
                root = LayoutInflater.from(context).inflate(R.layout.date_layout, viewGroup, false);
                return new DateViewHolder(root);
            case OTHER_TYPE:
                root = LayoutInflater.from(context).inflate(R.layout.other_layout, viewGroup, false);
                return new OtherViewHolder(root);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        switch (itemViewType) {
            case BANNER_TYPE:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
                bannerViewHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setBannerTitles(bannerTitle)
                        .setImages(topStoriesBeans)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                DailyListBean.TopStoriesBean topStoriesBean = (DailyListBean.TopStoriesBean) path;
                                Glide.with(context).load(topStoriesBean.getImage()).into(imageView);
                            }
                        }).start();
                break;
            case DATE_TYPE:
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;
                dateViewHolder.textDate.setText(date);
                break;
            case OTHER_TYPE:
                if (topStoriesBeans.size() != 0) {
                    i = i - 1;
                }
                if (!TextUtils.isEmpty(date)) {
                    i = i - 1;
                }

                DailyListBean.StoriesBean storiesBean = storiesBeans.get(i);
                OtherViewHolder otherViewHolder = (OtherViewHolder) viewHolder;
                otherViewHolder.title.setText(storiesBean.getTitle());
                Glide.with(context).load(storiesBean.getImages().get(0)).into(otherViewHolder.img);
                int id = storiesBean.getId();
                EventBus.getDefault().postSticky(new DetailEvent(id));
                otherViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, DetailActivity.class));
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return topStoriesBeans.size() > 0 ? storiesBeans.size() + 1 : storiesBeans.size();
    }

    static
    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;

        BannerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static
    class OtherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;

        OtherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TextDate)
        TextView textDate;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
