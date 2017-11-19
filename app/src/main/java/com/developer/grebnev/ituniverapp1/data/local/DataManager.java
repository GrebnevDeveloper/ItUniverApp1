package com.developer.grebnev.ituniverapp1.data.local;

import com.activeandroid.ActiveAndroid;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Grebnev on 04.11.2017.
 */
@Singleton
public class DataManager implements DataManagerInterface {
    @Inject
    public DataManager() {}

    @Override
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

    @Override
    public void overwriteData(List<Vacancy> vacancies, int totalItemCount) {
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < vacancies.size(); i++) {
                Vacancy vacancy = Vacancy.load(Vacancy.class, (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD + i + 1));
                vacancy.setAddress(vacancies.get(i).getAddress());
                vacancy.setCreatedAt(vacancies.get(i).getCreatedAt());
                vacancy.setIdVacancy(vacancies.get(i).getIdVacancy());
                vacancy.setName(vacancies.get(i).getName());
                vacancy.setSnippet(vacancies.get(i).getSnippet());
                vacancy.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
