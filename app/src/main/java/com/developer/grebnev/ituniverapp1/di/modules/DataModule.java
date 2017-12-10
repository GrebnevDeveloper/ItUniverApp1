package com.developer.grebnev.ituniverapp1.di.modules;

import com.developer.grebnev.ituniverapp1.data.local.DataManager;
import com.developer.grebnev.ituniverapp1.data.local.DataManagerInterface;
import com.developer.grebnev.ituniverapp1.data.local.DataQuery;
import com.developer.grebnev.ituniverapp1.data.local.DataQueryInterface;
import com.developer.grebnev.ituniverapp1.data.network.ApiConstants;
import com.developer.grebnev.ituniverapp1.data.network.RequestInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Module
public class DataModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public RequestInterface provideRequest(Retrofit retrofit) {
        return retrofit.create(RequestInterface.class);
    }

    @Singleton
    @Provides
    public DataQueryInterface provideQuery(DataQuery dataQuery) {
        return dataQuery;
    }

    @Singleton
    @Provides
    public DataManagerInterface provideDataManager(DataManager dataManager) {
        return dataManager;
    }
}
