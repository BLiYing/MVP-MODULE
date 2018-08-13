package xyz.windback.basesdk.http;



import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.windback.basesdk.BuildConfig;
import xyz.windback.basesdk.base.Constants.Constant;
import xyz.windback.basesdk.utils.https.HttpsUtils;

/**
 * Describe:配置api
 * Created by liying on 2018/3/5
 */
public class Api {
    private static Api api;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static FastJsonConvertFactory fastJsonConverterFactory = FastJsonConvertFactory.create();//fastjson解析封装
    private static RxJavaCallAdapterFactory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();//默认gson解析
    private Retrofit retrofit;

    private Api(){
        retrofit = new Retrofit.Builder()
                .client(defaultOkHttpClient())//默认设置
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    /** 单例模式 */
    public static Api getInstance(){
        if (api == null) {
            synchronized (Api.class){
                if (api == null) {
                    api = new Api();
                }
            }
        }
        return api;
    }

    private OkHttpClient defaultOkHttpClient() {
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

    /*public static ApiGirlsService getGankApiService() {
        if (sApiGirls == null) {
            //或者手动创建一个OkHttpClient并设置超时时间
            sApiGirls = new Retrofit.Builder()
                    .client(BaseApplication.defaultOkHttpClient())//默认设置
                    .baseUrl(Url.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build().create(ApiGirlsService.class);
        }
        return sApiGirls;
    }*/

    /**
     * 根据需要自己编写自己模块的网络请求Api
     * 泛型实现
     * @return
     */
    public <T> T getApiService(Class<T> apiServer){
        return retrofit.create(apiServer);
    }


}
