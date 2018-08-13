package com.tepia.reservoir.mvp.presenter;

import com.tepia.reservoir.mvp.contract.MyOrderContract;
import com.tepia.reservoir.mvp.model.MyOrderModel;

import xyz.windback.basesdk.base.baseMvp.BasePresenter;

/**
 * Created by liying on 2018-3-14
 */
public class MyOrderPresenter extends BasePresenter<MyOrderContract.View, MyOrderContract.Model> implements MyOrderContract.Presenter {
    private MyOrderModel model;

    public MyOrderPresenter(MyOrderContract.View view) {
        this.model = new MyOrderModel();
        this.attachViewM(view, model);
    }

    @Override
    public void getOrderList() {
        model.getOrderList(this);
    }

    @Override
    public void modelSuccess(String state, String request) {
        getView().loadDataSuccess(request, "");
    }

    @Override
    public MyOrderContract.Model getModel() {
        return model;
    }
}
