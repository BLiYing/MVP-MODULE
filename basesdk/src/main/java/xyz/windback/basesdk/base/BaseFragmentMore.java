package xyz.windback.basesdk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.windback.basesdk.base.baseMvp.BaseMvpInterface;

/**
 * Class description
 * 基础的BaseFragment抽象类
 *
 * @author WJ
 * @version 1.0, 2018-3-8
 */

public abstract class BaseFragmentMore  extends BaseFragment implements BaseMvpInterface {


    public View rootView;

    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beforeInitView(savedInstanceState);

        if (rootView != null) {
            afterInitView(rootView);
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(getLayoutId(), container, false);
            afterInitView(rootView);
            initView(savedInstanceState,rootView);
            initData(savedInstanceState,rootView);
        }


        return rootView;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()) onWidgetClick(view);
    }


    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }
}
