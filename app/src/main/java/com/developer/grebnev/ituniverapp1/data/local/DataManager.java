package com.developer.grebnev.ituniverapp1.data.local;

import com.activeandroid.ActiveAndroid;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Grebnev on 04.11.2017.
 */
@Singleton
public class DataManager {
    @Inject
    public DataManager() {}

    public void saveData(List<Vacancy> vacancies) {
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < vacancies.size(); i++) {
                Vacancy vacancy = new Vacancy();
                vacancy = vacancies.get(i);
                vacancy.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
