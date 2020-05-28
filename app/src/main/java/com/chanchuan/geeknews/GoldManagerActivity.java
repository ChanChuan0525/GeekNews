package com.chanchuan.geeknews;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.chanchuan.Utils.Constants;
import com.chanchuan.adpater.GoldAdapter;
import com.chanchuan.base.BaseActivity;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.eventbus.TabEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;

import butterknife.BindView;

public class GoldManagerActivity extends BaseActivity {
    @BindView(R.id.goldManager_toolBar)
    Toolbar goldManagerToolBar;
    @BindView(R.id.goldManager_recycler)
    RecyclerView goldManagerRecycler;

    /**
     * 可以操作RecyclerView
     */
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            return makeMovementFlags(dragFlags, 0);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            int oldPos = viewHolder.getAdapterPosition();
            int newPos = viewHolder1.getAdapterPosition();
            Collections.swap(Constants.title, oldPos, newPos);
            Collections.swap(Constants.isSelected, oldPos, newPos);
            goldAdapter.notifyItemMoved(oldPos, newPos);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }
    });
    private GoldAdapter goldAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(goldManagerToolBar);
        goldManagerRecycler.setLayoutManager(new LinearLayoutManager(this));
        goldManagerRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        goldAdapter = new GoldAdapter(this);
        goldManagerRecycler.setAdapter(goldAdapter);
        /**
         * 绑定RecyclerView
         */
        itemTouchHelper.attachToRecyclerView(goldManagerRecycler);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_gold_manager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new TabEvent());
    }
}
