package com.tepia.reservoir.mvp.model;

import android.content.Context;

import com.tepia.reservoir.activity.MainActivityNew;
import com.tepia.reservoir.entity.HomeGirlsEntityParcelable;
import com.tepia.reservoir.entity.HttpResultHomeDemo;
import com.tepia.reservoir.http.HttpUtil;

import com.tepia.reservoir.http.homeapiservice.ApiGirlsService;
import com.tepia.reservoir.mvp.contract.HomeContract;
import com.tepia.reservoir.mvp.presenter.HomePresent;

import java.util.List;

import rx.Observable;
import xyz.windback.basesdk.base.ActivityLifeCycleEvent;
import xyz.windback.basesdk.http.Api;
import xyz.windback.basesdk.http.cache.ProgressSubscriber;

/**
 * Created by liying on 2018-1-22.
 */
public class HomeModel implements HomeContract.Model {

    private HomePresent prerenter;
    private Context context;

    public HomeModel(Context context,HomePresent prerenter){
        this.prerenter = prerenter;
        this.context = context;
    }

    @Override
    public void getGirlsOfModel(int count, int page,
                                boolean isfresh,
                                String cacheKey,
                                boolean issave,
                                boolean forceRefresh,
                                boolean isShowDialog) {
            getGirls(count, page, isfresh, cacheKey, issave, forceRefresh, isShowDialog);


    }

    private void getGirls(int count, int page,
                          final boolean isfresh,
                          final String cacheKey,
                          final boolean issave,
                          final boolean forceRefresh,
                          final boolean isShowDialog) {
        Observable<HttpResultHomeDemo<List<HomeGirlsEntityParcelable>>> ob = Api.getInstance().getApiService(ApiGirlsService.class).getBeauties(count, page);
        HttpUtil.getInstance().toSubscribe(ob, new ProgressSubscriber<List<HomeGirlsEntityParcelable>>(context) {
                    @Override
                    protected void _onError(String message) {
                        prerenter.showErrorOfPresent(isfresh);
                    }

                    @Override
                    protected void _onNext(List<HomeGirlsEntityParcelable> list) {
                       prerenter.showGirlsOfPresent(isfresh,list);

                    }

                },
                cacheKey,
                ActivityLifeCycleEvent.CREATE,
                MainActivityNew.getInstance().getLifeSubject(),
                issave,
                forceRefresh,
                isShowDialog);
        /**
         * 取消该次请求
         */
//        HttpUtil.getInstance().bindUntilEvent(ActivityLifeCycleEvent.CREATE,MainActivityNew.getInstance().getLifeSubject());
    }
}
