package com.tepia.reservoir.mvp.model;

import com.tepia.reservoir.mvp.contract.MyOrderContract;

/**
 * Created by liying on 2018-3-14
 */
public class MyOrderModel implements MyOrderContract.Model {

    @Override
    public void getOrderList(MyOrderContract.Presenter presenter) {
        presenter.modelSuccess("11111", "11111");
    }
}
