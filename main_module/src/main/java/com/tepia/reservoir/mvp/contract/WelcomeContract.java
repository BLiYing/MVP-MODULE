package com.tepia.reservoir.mvp.contract;

import android.content.Context;

import xyz.windback.basesdk.base.baseMvp.BaseModel;
import xyz.windback.basesdk.base.baseMvp.IBaseView;

/**
 * Created by khj on 2018-3-12.
 */

public interface WelcomeContract {
    interface Model extends BaseModel {
        void getServerTime(Context context, Presenter presenter);
    }

    interface View extends IBaseView {

    }

    interface Presenter {
        void onSuccess(String state, String request);

        void getServerTime(Context context);
    }
}
