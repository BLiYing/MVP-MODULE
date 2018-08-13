package xyz.windback.basesdk.base;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import xyz.windback.basesdk.BuildConfig;
import xyz.windback.basesdk.base.Constants.Constant;
import xyz.windback.basesdk.http.HttpLoggingInterceptor;
import xyz.windback.basesdk.utils.AppUtil;
import xyz.windback.basesdk.utils.LogUtil;
import xyz.windback.basesdk.utils.Utils;
import xyz.windback.basesdk.utils.https.HttpsUtils;


/**
 * Created by BLiYing on 2018/5/22.
 */

public class BaseMaxApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//初始化贯穿整个生命周期的context
        Hawk.init(this).build();//初始化加密数据库
        //配置是否显示log
        LogUtil.isDebug = true;
        //Realm 进行数据库操作
       /* Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(1) // Must be bumped when the schema changes
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);*/
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
        /*builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return false;
            }
        });*/
        client = builder.connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
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

}
