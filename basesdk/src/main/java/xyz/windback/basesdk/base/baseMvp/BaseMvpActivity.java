package xyz.windback.basesdk.base.baseMvp;

import android.os.Bundle;

import xyz.windback.basesdk.base.BaseAppCompatActivity;

/**
 * Created by khj on 2018-3-7.
 */

public abstract class BaseMvpActivity<P extends BasePresenter, M extends BaseModel> extends BaseAppCompatActivity implements IBaseView {
    /**
     * presenter 具体的presenter由子类确定
     */
    protected P mPresenter;

    /**
     * model 具体的model由子类确定
     */
    private M mIMode;

    /**
     * 初始化Presenter
     *
     * @return
     */
    public abstract P initPresenter();

    /**
     * 初始化数据
     * <p>
     * 子类可以复写此方法初始化子类数据
     */
    public void initData(Bundle savedInstanceState) {
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIMode = (M) mPresenter.getModel();
            if (mIMode != null) {
                mPresenter.attachViewM(this, mIMode);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachViewM();
        }
    }
    @Override
    public void toast(String msg, int requestTag) {

    }
}
