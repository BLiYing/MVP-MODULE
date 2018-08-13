package com.tepia.reservoir.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import xyz.windback.basesdk.base.BaseFragmentMore;
import xyz.windback.basesdk.base.Constants.CacheKey;

import com.tepia.reservoir.entity.HomeGirlsEntityParcelable;
import com.tepia.reservoir.mvp.contract.HomeContract;
import com.tepia.reservoir.mvp.presenter.HomePresent;
import com.tepia.reservoir.view.MySettingView;
import com.tepia.reservoir.R;

import java.util.List;

import xyz.windback.basesdk.base.Constants.AppRoutePath;
import xyz.windback.basesdk.utils.ToastUtils;


/**
 * Describe:我的fragment
 * Created by liying on 2018/3/5
 */
public class MineFragment extends BaseFragmentMore implements HomeContract.View{

    TextView toolbarTitle;
    MySettingView cardMv;
    MySettingView orderMv;
    Toolbar toolbar;
    MySettingView etcMv;
    MySettingView pointMv;
    MySettingView messageMv;
    MySettingView questionMv;
    MySettingView aboutMv;
    LinearLayout loginLy;
    Button logout;
    TextView name;

    private Context mContext;

    private HomePresent homePresent;

    public static MineFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        homePresent = new HomePresent(mContext, this);

    }


    /**
     * 初始化布局
     */
    private void init() {
        cardMv = rootView.findViewById(R.id.cardMv);
        cardMv.setIvLeft(R.drawable.mine_card);
        cardMv.setTitle(getString(R.string.minecard));
        cardMv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePresent.getGirlsOfPresent(10, 1, true, CacheKey.FIRSTGETGIRLS, true, true, true);

                homePresent.getGirlsOfPresent(10, 1, true, CacheKey.FIRSTGETGIRLS, true, true, true);

            }
        });
        /*toolbarTitle.setText(R.string.main_mine);
        cardMv.setIvLeft(R.drawable.mine_card);
        cardMv.setTitle(getString(R.string.minecard));
        orderMv.setIvLeft(R.drawable.mine_order);
        orderMv.setTitle(getString(R.string.mineorder));
        etcMv.setIvLeft(R.drawable.mine_etc);
        etcMv.setTitle(getString(R.string.mineetc));
        pointMv.setIvLeft(R.drawable.mine_point);
        pointMv.setTitle(getString(R.string.minepoint));
        messageMv.setIvLeft(R.drawable.mine_message);
        messageMv.setTitle(getString(R.string.minemessage));
        questionMv.setIvLeft(R.drawable.mine_question);
        questionMv.setTitle(getString(R.string.minequestion));
        aboutMv.setIvLeft(R.drawable.mine_about);
        aboutMv.setTitle(getString(R.string.mineaboutas));*/
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void beforeInitView(Bundle savedInstanceState) {

    }

    @Override
    public void afterInitView(View rootView) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState, View rootView) {
        init();
    }

    @Override
    public void initData(Bundle bundle, View rootView) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void showGirls(boolean isfresh, List<HomeGirlsEntityParcelable> list) {
        if (list != null && list.size() > 0) {
            HomeGirlsEntityParcelable homeGirlsEntityParcelable = list.get(0);
            homeGirlsEntityParcelable.get_id();
            ToastUtils.shortToast(homeGirlsEntityParcelable.get_id());
        }
    }

    @Override
    public void showError(boolean isfresh) {

    }

    @Override
    public void showNormal() {

    }
}
