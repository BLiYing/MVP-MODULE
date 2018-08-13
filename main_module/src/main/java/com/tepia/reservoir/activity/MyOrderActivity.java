package com.tepia.reservoir.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tepia.reservoir.R;
import com.tepia.reservoir.mvp.contract.MyOrderContract;
import com.tepia.reservoir.mvp.model.MyOrderModel;
import com.tepia.reservoir.mvp.presenter.MyOrderPresenter;

import butterknife.ButterKnife;
import xyz.windback.basesdk.base.baseMvp.BaseMvpActivity;

/**
 * Describe:我的订单
 * Created by liying on 2018/3/6
 */
public class MyOrderActivity extends BaseMvpActivity<MyOrderPresenter, MyOrderModel> implements MyOrderContract.View {
    TextView toolbarTitle;
    Toolbar toolbar;
    RecyclerView easyRecyclerView;


    @Override
    public void beforeInitView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        easyRecyclerView = (RecyclerView) findViewById(R.id.myorder_recycle);

        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.order_title);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public MyOrderPresenter initPresenter() {
        return new MyOrderPresenter(this);
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void loadDataSuccess(String Data, String requestTag) {
    }

    @Override
    public void loadDataError(Throwable e, int requestTag) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        if (mPresenter != null)
            mPresenter.getOrderList();
    }
}
