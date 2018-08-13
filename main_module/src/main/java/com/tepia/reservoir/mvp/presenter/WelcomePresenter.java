package com.tepia.reservoir.mvp.presenter;

import android.content.Context;

import com.tepia.reservoir.mvp.contract.WelcomeContract;
import com.tepia.reservoir.mvp.model.WelcomeModel;

import xyz.windback.basesdk.base.baseMvp.BasePresenter;

/**
 * Created by khj on 2018-3-12.
 */

public class WelcomePresenter extends BasePresenter<WelcomeContract.View, WelcomeContract.Model> implements WelcomeContract.Presenter {
    private WelcomeContract.View view;
    private WelcomeModel model;

    public WelcomePresenter(WelcomeContract.View view) {
        this.view = view;
        model = new WelcomeModel();
    }

    @Override
    public void onSuccess(String state, String request) {
        view.loadDataSuccess(state, request);
    }

    @Override
    public void getServerTime(Context context) {
        model.getServerTime(context, this);
    }

    @Override
    public WelcomeContract.Model getModel() {
        return model;
    }
}
