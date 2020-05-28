package com.chanchuan.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chanchuan.Utils.Constants;
import com.chanchuan.geeknews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldAdapter extends RecyclerView.Adapter<GoldAdapter.ViewHolder> {
    Context context;

    public GoldAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(R.layout.gold_item, viewGroup, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String title = Constants.title.get(i);
        viewHolder.gold_item_text.setText(title);
        viewHolder.gold_item_switchCompat.setChecked(Constants.isSelected.get(i));
        viewHolder.gold_item_switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Constants.isSelected.set(i, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constants.title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gold_item_text)
        TextView gold_item_text;
        @BindView(R.id.gold_item_switch)
        SwitchCompat gold_item_switchCompat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
