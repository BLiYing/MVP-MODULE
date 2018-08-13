package com.tepia.reservoir.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.tepia.reservoir.mvp.contract.LoginContract;
import com.tepia.reservoir.mvp.model.LoginModel;
import com.tepia.reservoir.mvp.presenter.LoginPresenter;
import com.tepia.reservoir.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import xyz.windback.basesdk.base.baseMvp.BaseMvpFragment;
import xyz.windback.basesdk.utils.ToastUtils;
import xyz.windback.basesdk.view.ClearEditText;

/**
 * Class description
 * 账户登录页面
 *
 * @author WJ
 * @version 1.0, 2018-3-6
 */

public class AccountLoginFragment extends BaseMvpFragment<LoginPresenter, LoginModel> implements LoginContract.View {

    ClearEditText edPhone;
    ClearEditText edPsw;
    CheckBox checkVisible;
    ImageView ivWechat;
    ImageView ivWeiBo;
    ImageView ivQq;
    Button btnLogin;
    private Context mContext;
    private View rootView;

    private Unbinder unbinder;


    public static AccountLoginFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        AccountLoginFragment fragment = new AccountLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void beforeInitView(Bundle savedInstanceState) {

    }

    @Override
    public void afterInitView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account_login;
    }

    @Override
    public void initView(Bundle savedInstanceState, View rootView) {
        btnLogin.setOnClickListener(this);

    }


    @Override
    public void onWidgetClick(View view) {


    }




    @Override
    public void toast(String msg, int requestTag) {
        ToastUtils.shortToast(msg);
    }

    @Override
    public void loadDataSuccess(String Data, String requestTag) {
        if (requestTag.equals("login")) {
            ToastUtils.shortToast(Data);
            getActivity().finish();
        }
    }

    @Override
    public void loadDataError(Throwable e, int requestTag) {

    }


}
