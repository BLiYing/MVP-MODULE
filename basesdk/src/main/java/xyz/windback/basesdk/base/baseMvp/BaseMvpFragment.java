package xyz.windback.basesdk.base.baseMvp;

import android.os.Bundle;
import android.view.View;

import xyz.windback.basesdk.base.BaseFragmentMore;

/**
 * Class description
 * Mvp的baseFragment基础类
 *
 * @author WJ
 * @version 1.0, 2018-3-8
 */

public abstract  class BaseMvpFragment <Presenter extends BasePresenter, M extends BaseModel>  extends BaseFragmentMore  implements IBaseView{



    /**
     * presenter 具体的presenter由子类确定
     */
    protected Presenter mPresenter;

    /**
     * model 具体的model由子类确定
     */
    private M mIMode;

    /**
     * 初始化Presenter
     *
     * @return
     */
    public abstract Presenter initPresenter();

    /**
     * 初始化数据
     * <p>
     * 子类可以复写此方法初始化子类数据
     */
    public void initData(Bundle savedInstanceState,View rootView) {
        mPresenter = (Presenter) initPresenter();
        if (mPresenter != null) {
            mIMode = (M) mPresenter.getModel();
            if (mIMode != null) {
                mPresenter.attachViewM(this, mIMode);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachViewM();
        }
    }




}
