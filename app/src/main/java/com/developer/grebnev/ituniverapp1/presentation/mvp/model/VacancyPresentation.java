package com.developer.grebnev.ituniverapp1.presentation.mvp.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by Grebnev on 07.12.2017.
 */
@AutoValue
public abstract class VacancyPresentation implements Parcelable {
    public static VacancyPresentation create(String salary,
                                             String name,
                                             String description,
                                             String createdAt,
                                             String address,
                                             String employer,
                                             String email,
                                             String phone,
                                             String idVacancy) {
        return new AutoValue_VacancyPresentation(salary, name, description, createdAt, address, employer, email, phone, idVacancy);
    }

    public abstract String salary();

    public abstract String name();

    public abstract String description();

    public abstract String createdAt();

    public abstract String address();

    public abstract String employer();

    public abstract String email();

    public abstract String phone();

    public abstract String idVacancy();
}
