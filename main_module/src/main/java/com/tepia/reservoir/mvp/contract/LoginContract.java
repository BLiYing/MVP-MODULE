package com.tepia.reservoir.mvp.contract;

import com.tepia.reservoir.mvp.presenter.LoginPresenter;

import xyz.windback.basesdk.base.baseMvp.BaseModel;
import xyz.windback.basesdk.base.baseMvp.IBaseView;

/**
 * Class description
 * 登录接口管理类
 *
 * @author WJ
 * @version 1.0, 2018-3-6
 */

public interface LoginContract {
    interface Model extends BaseModel {

        void toLogin(String name, String psw, String loginType, LoginPresenter presenter);

        void toRegister(String name, String psw, String verify, LoginPresenter presenter);

        void toVerify(String name, String verifyType, LoginPresenter presenter);
    }

    interface View extends IBaseView {


    }

    interface Presenter {
        void toLogin(String name, String psw, String loginType);

        void toRegister(String name, String psw, String verify);

        void toVerify(String name, String verifyType);

        void showMassge();
    }
}
