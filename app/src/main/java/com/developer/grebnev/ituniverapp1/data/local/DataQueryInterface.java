package com.developer.grebnev.ituniverapp1.data.local;

import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 11.11.2017.
 */

public interface DataQueryInterface {
    Observable<List<VacancyLocal>> getListVacancies(int start, int end);

    Observable<Integer> getCountVacancies();
}
