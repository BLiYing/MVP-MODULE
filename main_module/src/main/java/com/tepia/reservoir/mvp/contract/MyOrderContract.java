package com.tepia.reservoir.mvp.contract;

import xyz.windback.basesdk.base.baseMvp.BaseModel;
import xyz.windback.basesdk.base.baseMvp.IBaseView;

/**
 * Created by liying on 2018-3-14
 */
public interface MyOrderContract {
    interface Model extends BaseModel {
        void getOrderList(Presenter presenter);
    }

    interface View extends IBaseView {

    }

    interface Presenter {
        void getOrderList();
        void modelSuccess(String state, String request);
    }
}
