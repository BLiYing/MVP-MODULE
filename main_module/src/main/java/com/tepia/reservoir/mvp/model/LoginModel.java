package com.tepia.reservoir.mvp.model;

import com.tepia.reservoir.activity.RegisterLoginActivity;
import xyz.windback.basesdk.base.Constants.CacheKey;
import xyz.windback.basesdk.base.Constants.Constant;
import com.tepia.reservoir.entity.inputEntity.LoginInputEntity;
import com.tepia.reservoir.entity.outputEntity.LoginOutpuEntity;
import com.tepia.reservoir.http.HttpUtil;
import com.tepia.reservoir.http.loginapiservice.ApiLoginService;
import com.tepia.reservoir.mvp.contract.LoginContract;
import com.tepia.reservoir.mvp.presenter.LoginPresenter;


import rx.Observable;
import xyz.windback.basesdk.base.ActivityLifeCycleEvent;
import xyz.windback.basesdk.http.Api;
import xyz.windback.basesdk.http.cache.ProgressSubscriber;
import xyz.windback.basesdk.utils.LogUtil;
import xyz.windback.basesdk.utils.PreferencesUtils;
import xyz.windback.basesdk.utils.ToastUtils;
import xyz.windback.basesdk.utils.Utils;

/**
 * Class description
 * 登录数据交互类
 *
 * @author WJ
 * @version 1.0, 2018-3-6
 */

public class LoginModel implements LoginContract.Model {
    private String jmpsw;
    private String userPassJm = "";

    @Override
    public void toLogin(final String name, String psw, String loginType, final LoginPresenter presenter) {

        presenter.showMassge();
        // 传入网络请求参数
        LoginInputEntity loginInputEntity = new LoginInputEntity();

        Observable<LoginOutpuEntity> ob = Api.getInstance().getApiService(ApiLoginService.class).login(loginInputEntity);
        HttpUtil.getInstance().toSubscribe(ob, new ProgressSubscriber<LoginOutpuEntity>(RegisterLoginActivity.getInstance()) {
                    @Override
                    protected void _onError(String message) {
                        LogUtil.e("onError:::", message);
                    }

                    @Override
                    protected void _onNext(LoginOutpuEntity loginOutpuEntity) {
                        if (loginOutpuEntity.getRETCODE().equals("9000")) {
                            LogUtil.e("onNext:::", loginOutpuEntity.getRETMSG());
//                        userId = loginOutpuEntity.getUSERID();
                            PreferencesUtils.putString(Utils.getContext(), Constant.USERID, loginOutpuEntity.getUSERID());
                            PreferencesUtils.putString(Utils.getContext(), Constant.teminalId, loginOutpuEntity.getTEMINALID());
                            PreferencesUtils.removeKey(Utils.getContext(), Constant.CoardNo);
                            PreferencesUtils.putString(Utils.getContext(), Constant.USERNAME, name);
                            presenter.getView().loadDataSuccess(loginOutpuEntity.getRETMSG(), "login");
                        } else {
                                    ToastUtils.shortToast(loginOutpuEntity.getRETMSG());
                        }
                    }
                },
                CacheKey.A,
                ActivityLifeCycleEvent.CREATE,
                RegisterLoginActivity.getInstance().getLifeSubject(),
                false,
                true,
                true);
    }

    @Override
    public void toRegister(String name, String psw, String verify, final LoginPresenter presenter) {

    }

    @Override
    public void toVerify(String name, String verifyType, final LoginPresenter presenter) {
    }
}
