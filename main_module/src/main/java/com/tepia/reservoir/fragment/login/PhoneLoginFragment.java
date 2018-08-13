package com.tepia.reservoir.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tepia.reservoir.R;
import com.tepia.reservoir.mvp.contract.LoginContract;
import com.tepia.reservoir.mvp.model.LoginModel;
import com.tepia.reservoir.mvp.presenter.LoginPresenter;
import com.tepia.reservoir.util.CountDownButtonUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import xyz.windback.basesdk.base.baseMvp.BaseMvpFragment;
import xyz.windback.basesdk.utils.ToastUtils;
import xyz.windback.basesdk.view.ClearEditText;

/**
 * Class description
 * 手机验证码登录
 *
 * @author WJ
 * @version 1.0, 2018-3-6
 */

public class PhoneLoginFragment extends BaseMvpFragment<LoginPresenter, LoginModel> implements LoginContract.View {


    ClearEditText edPhone;
    ClearEditText edCode;
    Button btnCode;
    Unbinder unbinder1;
    Button btnLogin;
    private Context mContext;
    private View rootView;
    private Unbinder unbinder;

    public static PhoneLoginFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        PhoneLoginFragment fragment = new PhoneLoginFragment();
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
        unbinder1.unbind();
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
        return R.layout.fragment_phone_login;
    }

    @Override
    public void initView(Bundle savedInstanceState, View rootView) {
        mContext = getActivity();
        btnCode.setOnClickListener(this);
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
        ToastUtils.shortToast(Data);
        switch (requestTag) {
            case "verify":
                CountDownButtonUtils countDownButtonUtils = new CountDownButtonUtils(btnCode, getString(R.string.get_message_code), "s", 60, 1);
                countDownButtonUtils.start();
                break;
            case "register":
                break;
        }
    }

    @Override
    public void loadDataError(Throwable e, int requestTag) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
