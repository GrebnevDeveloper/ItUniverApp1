package com.developer.grebnev.ituniverapp1.di.modules;

import com.developer.grebnev.ituniverapp1.data.network.ApiConstants;
import com.developer.grebnev.ituniverapp1.data.network.RequestInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Module
public class VacancyModule {
    @Provides
    public Gson provideGsonBuilder() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public RequestInterface provideRequest(Retrofit retrofit) {
        return retrofit.create(RequestInterface.class);
    }
}
