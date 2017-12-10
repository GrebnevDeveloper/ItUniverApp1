package com.developer.grebnev.ituniverapp1.data.local;


import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 11.11.2017.
 */

public interface DataManagerInterface {
    Observable saveData(List<Vacancy> vacancies);
    Observable overwriteData(List<Vacancy> vacancies, int totalItemCount);
}
