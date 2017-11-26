package com.developer.grebnev.ituniverapp1.data.network;

import com.developer.grebnev.ituniverapp1.data.network.model.PageVacancyNetwork;
import com.developer.grebnev.ituniverapp1.data.network.model.VacancyNetwork;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Grebnev on 02.11.2017.
 */

public interface RequestInterface {
    @GET("/vacancies")
    Observable<PageVacancyNetwork> getVacancies(@Query("per_page") int countVacancies, @Query("page") int numberPage);

    @GET("/vacancies")
    Observable<PageVacancyNetwork> getResultSearch(@Query("text") String textSearch,
                                            @Query("per_page") int countVacancies,
                                            @Query("page") int numberPage);

    @GET("/vacancies/{vacancy_id}")
    Observable<VacancyNetwork> getVacancyDetail(@Path("vacancy_id") String vacancyId);
}
