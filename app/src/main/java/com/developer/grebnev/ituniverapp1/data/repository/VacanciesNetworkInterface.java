package com.developer.grebnev.ituniverapp1.data.repository;

import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 11.11.2017.
 */

public interface VacanciesNetworkInterface {
    Observable<List<Vacancy>> getVacanciesNetwork(int countVacancies, int numberPage);
}
