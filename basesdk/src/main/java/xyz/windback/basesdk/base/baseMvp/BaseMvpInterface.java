package xyz.windback.basesdk.base.baseMvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Class description
 * Fragment使用的基础Base接口
 *
 * @author WJ
 * @version 1.0, 2018-3-8
 */

public  interface BaseMvpInterface  extends View.OnClickListener{

    /**
     * 初始化 view前
     */
    void beforeInitView(final Bundle savedInstanceState);

    /**
     * 初始化 view后
     */
    void afterInitView(final View rootView);

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    @LayoutRes
    int getLayoutId();


    /**
     * 初始化 view
     */
    void initView(final Bundle savedInstanceState,View rootView);

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    void initData(final Bundle bundle,View rootView);


    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    void onWidgetClick(final View view);
}
