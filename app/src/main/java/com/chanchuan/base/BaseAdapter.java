package com.chanchuan.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    protected List<T> dataList = new ArrayList<>();

    public BaseAdapter(Context context) {
        this.context = context;
    }

    public void resetDataList(List<T> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addDataList(List<T> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(getItemLayout(), viewGroup, false);
        return new BaseViewHolder(root);
    }

    protected abstract int getItemLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
        bindData(baseViewHolder, dataList.get(i));
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iOnClikcItem != null) {
                    iOnClikcItem.clickItem(baseViewHolder, i);
                }
            }
        });
    }

    protected abstract void bindData(BaseViewHolder baseViewHolder, T t);

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected static class BaseViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> views;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            if (views == null) {
                views = new SparseArray<>();
            }
        }

        public View getViewById(int id) {
            View view = views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.append(id, view);
            }
            return view;
        }
    }

    IOnClikcItem iOnClikcItem;

    public void setiOnClikcItem(IOnClikcItem iOnClikcItem) {
        this.iOnClikcItem = iOnClikcItem;
    }

    public interface IOnClikcItem {
        void clickItem(BaseViewHolder viewHolder, int pos);
    }
}
