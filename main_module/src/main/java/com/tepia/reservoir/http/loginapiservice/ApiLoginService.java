// (c)2016 Flipboard Inc, All Rights Reserved.

package com.tepia.reservoir.http.loginapiservice;


import com.tepia.reservoir.entity.inputEntity.LoginInputEntity;

import com.tepia.reservoir.entity.inputEntity.RegisterInputEntity;

import com.tepia.reservoir.entity.outputEntity.LoginOutpuEntity;

import com.tepia.reservoir.entity.outputEntity.RegisterOutputEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Describe:girls Api接口
 * Created by liying on 2018/2/7.
 */
public interface ApiLoginService {

    /**
     * 注册
     */
    @POST("flonline/web/appIService")
    Observable<RegisterOutputEntity> register(@Body() RegisterInputEntity body);

    /**
     * 登录
     */
    @POST("flonline/web/appIService")
    Observable<LoginOutpuEntity> login(@Body() LoginInputEntity body);


}
