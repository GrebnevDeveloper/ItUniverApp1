package com.developer.grebnev.ituniverapp1.data.local;

import com.activeandroid.ActiveAndroid;
import com.developer.grebnev.ituniverapp1.data.local.mapper.VacancyLocalMapper;
import com.developer.grebnev.ituniverapp1.data.local.model.AddressLocal;
import com.developer.grebnev.ituniverapp1.data.local.model.EmployerLocal;
import com.developer.grebnev.ituniverapp1.data.local.model.SalaryLocal;
import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;
import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 04.11.2017.
 */
@Singleton
public class DataManager implements DataManagerInterface {
    private VacancyLocalMapper vacancyLocalMapper;

    @Inject
    public DataManager(VacancyLocalMapper vacancyLocalMapper) {
        this.vacancyLocalMapper = vacancyLocalMapper;
    }

    @Override
    public Observable saveData(List<Vacancy> vacancies) {
        return Observable.fromCallable(() -> vacancies)
                .map(vacanciesList -> vacancyLocalMapper.transformListToLocal(vacanciesList))
                .doOnNext(vacancyList -> {
                    ActiveAndroid.beginTransaction();
                    try {
                        for (int i = 0; i < vacancyList.size(); i++) {
                            AddressLocal address = new AddressLocal();
                            address = vacancyList.get(i).getAddress();
                            address.save();
                            EmployerLocal employer = new EmployerLocal();
                            employer = vacancyList.get(i).getEmployer();
                            employer.save();
                            SalaryLocal salary = new SalaryLocal();
                            salary = vacancyList.get(i).getSalary();
                            salary.save();
                            VacancyLocal vacancy = new VacancyLocal();
                            vacancy = vacancyList.get(i);
                            vacancy.save();
                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }
                });
    }

    @Override
    public Observable overwriteData(List<Vacancy> vacancies, int totalItemCount) {
        return Observable.fromCallable(() -> vacancies)
                .map(vacanciesList -> vacancyLocalMapper.transformListToLocal(vacanciesList))
                .doOnNext(vacanciesList -> {
                    ActiveAndroid.beginTransaction();
                    try {
                        for (int i = 0; i < vacanciesList.size(); i++) {
                            AddressLocal addressLocal = AddressLocal.load(AddressLocal.class,
                                    (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD + i + 1));
                            addressLocal.setCity(vacanciesList.get(i).getAddress().getCity());
                            addressLocal.setStreet(vacanciesList.get(i).getAddress().getStreet());
                            addressLocal.setBuilding(vacanciesList.get(i).getAddress().getBuilding());
                            addressLocal.save();
                            EmployerLocal employerLocal = EmployerLocal.load(EmployerLocal.class,
                                    (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD + i + 1));
                            employerLocal.setName(vacanciesList.get(i).getEmployer().getName());
                            employerLocal.save();
                            SalaryLocal salaryLocal = SalaryLocal.load(SalaryLocal.class,
                                    (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD + i + 1));
                            salaryLocal.setCurrency(vacanciesList.get(i).getSalary().getCurrency());
                            salaryLocal.setFrom(vacanciesList.get(i).getSalary().getFrom());
                            salaryLocal.setTo(vacanciesList.get(i).getSalary().getTo());
                            salaryLocal.save();
                            VacancyLocal vacancyLocal = VacancyLocal.load(VacancyLocal.class,
                                    (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD + i + 1));
                            vacancyLocal.setAddress(addressLocal);
                            vacancyLocal.setCreatedAt(vacanciesList.get(i).getCreatedAt());
                            vacancyLocal.setIdVacancy(vacanciesList.get(i).getIdVacancy());
                            vacancyLocal.setName(vacanciesList.get(i).getName());
                            vacancyLocal.setSalary(salaryLocal);
                            vacancyLocal.setEmployer(employerLocal);
                            vacancyLocal.save();
                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }
                });
    }
}
