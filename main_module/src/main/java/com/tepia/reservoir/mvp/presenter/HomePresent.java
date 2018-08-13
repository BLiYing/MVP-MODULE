package com.tepia.reservoir.mvp.presenter;


import android.content.Context;

import com.tepia.reservoir.entity.HomeGirlsEntityParcelable;
import com.tepia.reservoir.mvp.contract.HomeContract;
import com.tepia.reservoir.mvp.model.HomeModel;

import java.util.List;

/**
 * Created by liying on 2018-1-22.
 */
public class HomePresent implements HomeContract.Prerenter {
    private HomeContract.View view;
    private HomeModel moder;
    public HomePresent(Context context,HomeContract.View view){
        this.view = view;
        moder = new HomeModel(context,this);
    }

    @Override
    public void getGirlsOfPresent(int count, int page,
                                  boolean isfresh,
                                  String cacheKey,
                                  boolean issave,
                                  boolean forceRefresh,
                                  boolean isShowDialog) {
        moder.getGirlsOfModel(count, page,
        isfresh,
        cacheKey,
        issave,
        forceRefresh,
        isShowDialog);
    }

    @Override
    public void showGirlsOfPresent(boolean isfresh,List<HomeGirlsEntityParcelable> list) {
        if(view == null)
            return;
        view.showGirls(isfresh,list);
    }

    @Override
    public void showErrorOfPresent(boolean isfresh) {
        view.showError(isfresh);
    }

    @Override
    public void showNormalOfPresent() {
        view.showNormal();
    }

    @Override
    public void destroy() {
        view = null;
    }


}
