package com.chanchuan.geeknews;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.chanchuan.base.BaseActivity;
import com.chanchuan.base.BasePresenter;
import com.chanchuan.fragment.GankFragment;
import com.chanchuan.fragment.GoldFragment;
import com.chanchuan.fragment.V2EXFragment;
import com.chanchuan.fragment.WeChatFragment;
import com.chanchuan.fragment.ZhihuFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigation;
    private Fragment lastFragment;
    private ZhihuFragment zhihuFragment;
    private WeChatFragment weChatFragment;
    private GankFragment gankFragment;
    private GoldFragment goldFragment;
    private V2EXFragment v2EXFragment;
    private static final int ZHIHU_TYPE = 0;
    private static final int WECHAT_TYPE = 1;
    private static final int GANK_TYPE = 2;
    private static final int GOLD_TYPE = 3;
    private static final int V2EX_TYPE = 4;
    private FragmentManager supportFragmentManager;
    private int type;
    private MenuItem lastItem;

    @Override
    protected BasePresenter initPresenter() {
        return null;

    }

    @Override
    protected void initData() {
        zhihuFragment = new ZhihuFragment();
        weChatFragment = new WeChatFragment();
        gankFragment = new GankFragment();
        goldFragment = new GoldFragment();
        v2EXFragment = new V2EXFragment();
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.content, zhihuFragment)
                .add(R.id.content, weChatFragment)
                .add(R.id.content, gankFragment)
                .add(R.id.content, goldFragment)
                .add(R.id.content, v2EXFragment)
                .show(zhihuFragment)
                .hide(weChatFragment)
                .hide(gankFragment)
                .hide(goldFragment)
                .hide(v2EXFragment)
                .commit();
        lastFragment = zhihuFragment;
        lastItem = navigation.getMenu().findItem(R.id.zhiHu);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.zhiHu:
                        type = ZHIHU_TYPE;
                        break;
                    case R.id.weChat:
                        type = WECHAT_TYPE;
                        break;
                    case R.id.gank:
                        type = GANK_TYPE;
                        break;
                    case R.id.gold:
                        type = GOLD_TYPE;
                        break;
                    case R.id.v2ex:
                        type = V2EX_TYPE;
                        break;
                }
                switchFragment(menuItem);
                drawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    public Fragment getCurrFragment() {
        switch (type) {
            case ZHIHU_TYPE:
                if (zhihuFragment == null) {
                    zhihuFragment = new ZhihuFragment();
                }
                return zhihuFragment;
            case WECHAT_TYPE:
                if (weChatFragment == null) {
                    weChatFragment = new WeChatFragment();
                }
                return weChatFragment;
            case GANK_TYPE:
                if (gankFragment == null) {
                    gankFragment = new GankFragment();
                }
                return gankFragment;
            case GOLD_TYPE:
                if (goldFragment == null) {
                    goldFragment = new GoldFragment();
                }
                return goldFragment;
            case V2EX_TYPE:
                if (v2EXFragment == null) {
                    v2EXFragment = new V2EXFragment();
                }
                return v2EXFragment;
        }
        return null;
    }

    @Override
    protected void initView() {
        setTitle("知乎日报");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.close, R.string.open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                content.setX(navigation.getRight());
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    public void switchFragment(MenuItem menuItem) {
        Fragment currFragment = getCurrFragment();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (lastFragment == currFragment) {
            return;
        }
        fragmentTransaction.show(currFragment)
                .hide(lastFragment)
                .commit();
        lastFragment = currFragment;
        lastItem.setChecked(false);
        lastItem = menuItem;
        lastItem.setCheckable(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
