package com.tepia.reservoir.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tepia.reservoir.R;
import com.tepia.reservoir.fragment.MineFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;
import xyz.windback.basesdk.base.ActivityLifeCycleEvent;
import xyz.windback.basesdk.base.BaseAppCompatActivity;
import xyz.windback.basesdk.http.cache.SimpleLoadDialog;

/**
 * Describe:首页
 * Created by liying on 2018/3/6
 */
public class MainActivityNew extends BaseAppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    FrameLayout layFrame;
    BottomNavigationBar bottomNavigationBar;

    private ArrayList<Fragment> fragments;
    private Context mContext;

    private MineFragment settingFragment;

    private BadgeItem numberBadgeItem;
    private SimpleLoadDialog dialogHandler;
    public static MainActivityNew activity;

    public static MainActivityNew getInstance() {
        if (activity != null) {
            return activity;
        }
        return activity;
    }

    public PublishSubject<ActivityLifeCycleEvent> getLifeSubject() {
        return lifecycleSubject;
    }

    @Override
    public void beforeInitView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_new;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        layFrame = (FrameLayout) findViewById(R.id.layFrame);
        mContext = this;
        activity = this;
        initToolBar();
        dialogHandler = new SimpleLoadDialog(MainActivityNew.this, null, true);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.navigation_bg);
        bottomNavigationBar.setAutoHideEnabled(false);

        /*numberBadgeItem = new BadgeItem(){}
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("5")
                .setHideOnSelect(true)
                .setBorderColorResource(R.color.transparent);*/
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.main_home, R.string.main_home).setActiveColorResource(R.color.red))
//                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "Books").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.main_date, R.string.main_date).setActiveColorResource(R.color.red))
//                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "Movies & TV").setActiveColorResource(R.color.brown).setTextBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.main_mine, R.string.main_mine).setActiveColorResource(R.color.red))
                .setFirstSelectedPosition(0)
                .initialise();

        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.toggle();
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected boolean isShowBacking() {
        return false;
    }


    private void initToolBar() {
        Drawable drawableLeft = ContextCompat.getDrawable(this,
                R.drawable.main_search_icon);
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());


    }

    /**
     * 切换fragment时更改主题
     *
     * @param title
     */
    private void initToolBarOther(String title) {
//        imageOfCollapsingIv.setVisibility(View.GONE);


    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        settingFragment = MineFragment.newInstance("");
        transaction.replace(R.id.layFrame, settingFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                initToolBar();
                if (settingFragment == null) {
                    settingFragment = (MineFragment.newInstance(""));
                }

                transaction.replace(R.id.layFrame, settingFragment);
                transaction.commitAllowingStateLoss();
                break;

            case 1:
                initToolBarOther(getString(R.string.main_date));
                if (settingFragment == null) {
                    settingFragment = MineFragment.newInstance("");
                }
                transaction.replace(R.id.layFrame, settingFragment);
                transaction.commitAllowingStateLoss();
                break;

            case 2:
                initToolBarOther(getString(R.string.main_mine));
                if (settingFragment == null) {
                    settingFragment = MineFragment.newInstance("");
                }
                transaction.replace(R.id.layFrame, settingFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }


    }


    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
