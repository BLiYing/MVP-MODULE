package com.tepia.reservoir.application;

import com.orhanobut.hawk.Hawk;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import xyz.windback.basesdk.BuildConfig;
import xyz.windback.basesdk.base.BaseMaxApplication;
import xyz.windback.basesdk.http.HttpLoggingInterceptor;
import xyz.windback.basesdk.utils.LogUtil;
import xyz.windback.basesdk.utils.https.HttpsUtils;

/**
 * Created by helin on 2016/11/11 11:15.
 */

public class BaseApplication extends BaseMaxApplication implements Thread.UncaughtExceptionHandler {
    private static BaseApplication mApplication;
    /**
     * 请求超时时间
     */
    public static final int DEFAULT_TIMEOUT = 10000;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
//        appInitARouter();

        //配置程序异常退出处理
//        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));

    }


    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * HttpLoggingInterceptor1 是一个拦截器，用于输出网络请求和结果的 Log，
         * 可以配置 level 为 BASIC / HEADERS / BODY，都很好理解，
         * 对应的是原来 retrofit 的 set log level 方法
         * DEBUG模式下打印
         */
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY));
        }

        client = builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)//错误重连
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.createSSLSocketFactory(), new HttpsUtils.TrustAllCerts())
                .build();
        return client;
    }



    public static BaseApplication getInstance() {
        return mApplication;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }
}
