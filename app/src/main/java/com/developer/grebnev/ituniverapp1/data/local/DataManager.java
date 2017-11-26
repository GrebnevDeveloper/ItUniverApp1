package com.developer.grebnev.ituniverapp1.data.local;

import com.activeandroid.ActiveAndroid;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;

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
                VacancyLocal vacancy = new VacancyLocal();
                fromNetworkToLocal(vacancies.get(i), vacancy);
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
                VacancyLocal vacancyLocal = VacancyLocal.load(VacancyLocal.class, (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD + i + 1));
                fromNetworkToLocal(vacancies.get(i), vacancyLocal);
                vacancyLocal.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    private void fromNetworkToLocal(Vacancy vacancy, VacancyLocal vacancyLocal) {
        vacancyLocal.setAddress(vacancy.address());
        vacancyLocal.setCreatedAt(vacancy.createdAt());
        vacancyLocal.setIdVacancy(vacancy.idVacancy());
        vacancyLocal.setName(vacancy.name());
        //vacancyLocal.setSnippet(vacancy.getSnippet());
    }
}
