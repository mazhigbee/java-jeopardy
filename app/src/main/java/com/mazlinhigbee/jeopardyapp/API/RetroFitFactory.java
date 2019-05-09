package com.mazlinhigbee.jeopardyapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * com.mazlinhigbee.jeopardyapp.API
 * Created by: mhigbee
 * Date: 4/21/19 Time: 10:42 PM
 */
public class RetroFitFactory {
    public static Retrofit getRetroFit() {
        return new Retrofit.Builder()
                .baseUrl("http://jservice.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
