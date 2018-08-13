package com.tepia.reservoir.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.tepia.reservoir.R;
import com.tepia.reservoir.adapter.LoginPageAdapter;
import com.tepia.reservoir.fragment.login.AccountLoginFragment;
import com.tepia.reservoir.fragment.login.PhoneLoginFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;
import xyz.windback.basesdk.base.ActivityLifeCycleEvent;
import xyz.windback.basesdk.base.BaseAppCompatActivity;
import xyz.windback.basesdk.view.TabLayoutUtil;

/**
 * Class description
 * 登陆页
 *
 * @author WJ
 * @version 1.0, 2018-3-6
 */

public class RegisterLoginActivity extends BaseAppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static RegisterLoginActivity activity;

    public static RegisterLoginActivity getInstance() {
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
        return R.layout.activity_registerlogin;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        activity = this;
        ButterKnife.bind(this);
        setToolBarTitle(getResources().getString(R.string.login_title));
        initViewPager();
        TabLayoutUtil.setIndicator(tabLayout, 20, 20);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    private void initViewPager() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(AccountLoginFragment.newInstance(""));
        fragments.add(PhoneLoginFragment.newInstance(""));
        ArrayList<String> tiltes = new ArrayList<>();
        tiltes.add(getString(R.string.login_title));
        tiltes.add(getString(R.string.phone_login_subtitle));
        // 创建ViewPager适配器
        LoginPageAdapter loginPageAdapter = new LoginPageAdapter(getSupportFragmentManager());
        loginPageAdapter.setFragments(fragments);
        loginPageAdapter.setTitles(tiltes);
        // 给ViewPager设置适配器
        viewPager.setAdapter(loginPageAdapter);
        // 使用 TabLayout 和 ViewPager 相关联
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setToolBarTitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
