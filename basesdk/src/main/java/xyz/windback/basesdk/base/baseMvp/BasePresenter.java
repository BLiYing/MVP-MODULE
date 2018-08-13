package xyz.windback.basesdk.base.baseMvp;

/**
 * Class description
 * 基础业务处理层
 *
 * @author WJ
 * @version 1.0, 2018-1-10
 */

public abstract class BasePresenter <V extends IBaseView,M>  {

    /**
     * 绑定的view
     */
    private V mMvpView;
    private M mIModel;

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachViewM(V mMvpView,M mIModel) {
        this.mMvpView = mMvpView;
        this.mIModel = mIModel;
    }
    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachViewM() {
        this.mMvpView = null;
        this.mIModel = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached(){
        return mMvpView != null;
    }

    /**
     * 是否与model建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与model建立连接
     */
    public boolean isModelAttached(){
        return mIModel != null;
    }

    /**
     * 获取连接的view
     */
    public V getView(){
        return mMvpView;
    }


    /**
     * 返回presenter想持有的Model引用
     *
     * @return presenter持有的Model引用
     */
    public abstract M getModel();

}
