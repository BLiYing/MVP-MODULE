package com.tepia.shuiku.application;

import android.support.multidex.MultiDex;


import xyz.windback.basesdk.base.BaseMaxApplication;

/**
 * Created by helin on 2016/11/11 11:15.
 */

public class BaseApplication extends BaseMaxApplication{
    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //突破65535的限制
        MultiDex.install(this);
//        appInitARouter();
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }

}
