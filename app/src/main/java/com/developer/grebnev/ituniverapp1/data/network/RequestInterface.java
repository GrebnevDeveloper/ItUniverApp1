package com.developer.grebnev.ituniverapp1.data.network;

import com.developer.grebnev.ituniverapp1.data.entity.PageVacancy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grebnev on 02.11.2017.
 */

public interface RequestInterface {
    @GET("/vacancies")
    Call<PageVacancy> getVacancies(@Query("per_page") int countVacancies);
}
