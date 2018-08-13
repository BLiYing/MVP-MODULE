package com.tepia.reservoir.http.homeapiservice;

import com.tepia.reservoir.entity.HomeGirlsEntityParcelable;
import com.tepia.reservoir.entity.HttpResultHomeDemo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by khj on 2018-3-15.
 */

public interface ApiGirlsService {
    @GET("data/福利/{number}/{page}")
    Observable<HttpResultHomeDemo<List<HomeGirlsEntityParcelable>>> getBeauties(@Path("number") int number, @Path("page") int page);
}
