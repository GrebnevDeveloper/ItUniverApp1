package com.developer.grebnev.ituniverapp1.data.network;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grebnev on 02.11.2017.
 */

public class RetrofitManager {
    private static final String URL = "https://api.hh.ru/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                .build();
    }

    public static RequestInterface getRequestInterface() {
        return getRetrofitInstance().create(RequestInterface.class);
    }
}
