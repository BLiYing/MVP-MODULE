package com.tepia.reservoir.mvp.presenter;

import com.tepia.reservoir.mvp.contract.LoginContract;
import com.tepia.reservoir.mvp.model.LoginModel;

import xyz.windback.basesdk.base.baseMvp.BasePresenter;

/**
 * Class description
 * 登录主持连接类
 *
 * @author WJ
 * @version 1.0, 2018-3-6
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginModel moder;
    private LoginModel model;

    public LoginPresenter(LoginContract.View view) {
        model = new LoginModel();
        this.attachViewM(view, model);
    }


    @Override
    public LoginContract.Model getModel() {
        return model;
    }

    @Override
    public void toLogin(String name, String psw, String loginType) {
        model.toLogin(name, psw, loginType, this);
    }

    @Override
    public void toRegister(String name, String psw, String verify) {
        model.toRegister(name, psw, verify, this);
    }

    @Override
    public void toVerify(String name, String verifyType) {
        model.toVerify(name, verifyType, this);
    }

    @Override
    public void showMassge() {
        getView().toast("登录了", 0);
    }
}
