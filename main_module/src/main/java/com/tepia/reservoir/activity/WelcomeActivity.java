package com.tepia.reservoir.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import com.tepia.reservoir.mvp.contract.WelcomeContract;
import com.tepia.reservoir.mvp.model.WelcomeModel;
import com.tepia.reservoir.mvp.presenter.WelcomePresenter;
import com.tepia.reservoir.view.otherview.PromptDialog;
import com.tepia.reservoir.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.yanzhenjie.permission.SettingService;

import java.util.List;

import rx.subjects.PublishSubject;
import xyz.windback.basesdk.base.ActivityLifeCycleEvent;
import xyz.windback.basesdk.base.baseMvp.BaseMvpActivity;
import xyz.windback.basesdk.utils.LogUtil;
import xyz.windback.basesdk.utils.second.UUIDS;

/**
 * Describe:欢迎页
 * Created by liying on 2018/3
 */
public class WelcomeActivity extends BaseMvpActivity<WelcomePresenter, WelcomeModel> implements WelcomeContract.View {
    /**
     * 权限判断
     */
    private String[] permassion = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private Rationale ration;
    private PromptDialog promptDialog;
    private final int SPLASH_SETTING_CODE = 100; // 去往Setting页面的返回码
    private final int SPLASH_PERMISSION_CODE = 200; // 请求权限码
    private final int SPLASH_DISPLAY_LENGHT = 1000; // 延迟三秒
    private static WelcomeActivity activity;

    public static WelcomeActivity getInstance() {
        return activity;
    }

    public PublishSubject<ActivityLifeCycleEvent> getLifeSubject() {
        return lifecycleSubject;
    }

    private void getVersion() {
        // TODO 添加相关代码
    }

    private void getSysParam() {
        // TODO 添加相关代码
    }

    @SuppressLint("MissingPermission")
    private void init_View() {
        UUIDS.buidleID().check();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivityNew.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    /**
     * Rationale功能是在用户拒绝一次权限后，再次申请时检测到已经申请过一次该权限了，允许开发者弹窗说明申请权限的目的，
     * 获取用户的同意后再申请权限，避免用户勾选不再提示，导致不能再次申请权限。的监听
     */
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            ration = rationale;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showRational();
                        }
                    });
                }
            }, 800);
//            AndPermission.rationaleDialog(Splash.this, rationale).show();
            // 此对话框可以自定义，调用rationale.resume()就可以继续申请。
        }
    };

    private Handler handler = new Handler();

    /**
     * 再一次提示的Dialog
     */
    private void showRational() {
        String message = shouldHavePermission(true);
        promptDialog = new PromptDialog(WelcomeActivity.this)
                .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                .setAnimationEnable(true)
                .setSingle(true)
                .setTitleText(getString(R.string.prompt_info))
                .setContentText(message)
                .setPositiveListener(getString(R.string.excusme_sure), getString(R.string.btn_refuse), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                        ration.resume();
                    }

                    @Override
                    public void onCancleClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                });
        promptDialog.show();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndPermission.with(this)
                .requestCode(SPLASH_PERMISSION_CODE)
                .permission(permassion)
                .callback(listener)
                .rationale(rationaleListener)
                .start();
    }

    /**
     * 权限监听
     */
    public PermissionListener listener = new PermissionListener() {
        /**
         * 申请权限成功
         * @param requestCode
         * @param grantPermissions
         */
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            if (requestCode == SPLASH_PERMISSION_CODE) {
                if (AndPermission.hasPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    LogUtil.e("onSucceed -->", "HavePermission");
                    getVersion();
                    getSysParam();
                    init_View();
                } else {
                    LogUtil.e("onSucceed -->", "NotHavePermission");
                    // 第一种：用AndPermission默认的提示语。
                    showSettingDialog();
                }
            }
        }

        /**
         *  申请权限失败
         * @param requestCode
         * @param deniedPermissions
         */
        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            if (requestCode == SPLASH_PERMISSION_CODE) {
                if (AndPermission.hasPermission(WelcomeActivity.this, permassion)) {
                    // 执行操作。
                    LogUtil.e("onFailed -->", "HavePermission");
                    getVersion();
                    getSysParam();
                } else {
                    LogUtil.e("onFailed -->", "NotHavePermission");
                    // 是否有不再提示并拒绝的权限。
                    if (AndPermission.hasAlwaysDeniedPermission(WelcomeActivity.this, deniedPermissions)) {
                        LogUtil.e("onFailed -->", "allwaysNotHavePermission");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showSettingDialog();
                                    }
                                });
                            }
                        }, 800);
                    } else {
                        //获取权限
                        AndPermission.with(WelcomeActivity.this)
                                .requestCode(SPLASH_PERMISSION_CODE)
                                .permission(permassion)
                                .callback(listener)
                                .rationale(rationaleListener)
                                .start();
                    }
                }
            }
        }
    };

    /**
     * 若选择不再询问后的跳转设置页的Dialog
     */
    private void showSettingDialog() {
        final SettingService settingService = AndPermission.defineSettingDialog(WelcomeActivity.this, SPLASH_SETTING_CODE);
        String message = shouldHavePermission(false);
        promptDialog = new PromptDialog(WelcomeActivity.this)
                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                .setAnimationEnable(false)
                .setSingle(true)
                .setCanCancle(false)
                .setTitleText(getString(R.string.prompt_info))
                .setContentText(message)
                .setPositiveListener(getString(R.string.excusme_sure), getString(R.string.btn_refuse), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                        // 你的dialog点击了确定调用：
                        settingService.execute();
                    }

                    @Override
                    public void onCancleClick(PromptDialog dialog) {
                        dialog.dismiss();
                        // 你的dialog点击了取消调用：
                        settingService.cancel();
                    }
                });
        promptDialog.show();
    }

    /**
     * 检测授权失败后从Setting返回的操作
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SPLASH_SETTING_CODE: { // 这个就是你上面传入的数字。
                // 你可以在这里检查你需要的权限是否被允许，并做相应的操作。
                if (AndPermission.hasPermission(WelcomeActivity.this, permassion)) {
                    // 执行操作。
                    LogUtil.e("onActivityResult -->", "HavePermission");
                    getVersion();
                    getSysParam();
                    init_View();
                } else {
                    LogUtil.e("onActivityResult -->", "NotHavePermission");
                    showSettingDialog();
                }
                break;
            }
        }
    }

    /**
     * 拼接权限判断提示结果
     *
     * @return
     */
    private String shouldHavePermission(boolean isSHowRationale) {
        StringBuilder meaasge = new StringBuilder();
        boolean haveSD = AndPermission.hasPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean haveLoc = AndPermission.hasPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (isSHowRationale) {
            if (!haveSD) {
                meaasge.append(getString(R.string.permission_save_message) + "\n" + " \n");
            }
            if (!haveLoc) {
                meaasge.append(getString(R.string.permission_location_message) + "\n");
            }
        } else {
            if (!haveSD) {
                meaasge.append(getString(R.string.permission_save_no) + "\n" + " \n");
            }
            if (!haveLoc) {
                meaasge.append(getString(R.string.permission_location_no) + "\n" + " \n");
            }
            meaasge.append(getString(R.string.permission_path));
        }
        return meaasge.toString();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (promptDialog != null && promptDialog.isShowing()) {
            promptDialog.dismiss();
        }
    }

    @Override
    public void beforeInitView(Bundle savedInstanceState) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        activity = this;
    }

    @Override
    public WelcomePresenter initPresenter() {
        return new WelcomePresenter(this);
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void loadDataSuccess(String Data, String requestTag) {
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivityNew.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);*/
    }

    @Override
    public void loadDataError(Throwable e, int requestTag) {

    }
}
