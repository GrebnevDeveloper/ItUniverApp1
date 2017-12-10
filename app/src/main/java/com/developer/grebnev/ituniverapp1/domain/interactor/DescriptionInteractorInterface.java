package com.developer.grebnev.ituniverapp1.domain.interactor;


import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;

import io.reactivex.Observable;


/**
 * Created by Grebnev on 26.11.2017.
 */

public interface DescriptionInteractorInterface {
    Observable<Vacancy> getDetailVacancy(String vacancyId);
}
