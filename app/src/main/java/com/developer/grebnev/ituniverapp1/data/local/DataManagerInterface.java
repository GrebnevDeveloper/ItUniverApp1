package com.developer.grebnev.ituniverapp1.data.local;


import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.List;

/**
 * Created by Grebnev on 11.11.2017.
 */

public interface DataManagerInterface {
    void saveData(List<Vacancy> vacancies);
    void overwriteData(List<Vacancy> vacancies, int totalItemCount);
}
