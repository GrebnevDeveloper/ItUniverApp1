package com.developer.grebnev.ituniverapp1.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

/**
 * Created by Grebnev on 02.11.2017.
 */
@AutoValue
public abstract class Vacancy implements Parcelable{
    @Nullable public abstract Salary salary();
    public abstract String name();
    @Nullable public abstract String description();
    public abstract String createdAt();
    @Nullable public abstract Address address();
    @Nullable public abstract Employer employer();
    @Nullable public abstract Contacts contacts();
    public abstract String idVacancy();

    public static Vacancy create(Salary salary,
                                 String name,
                                 String description,
                                 String createdAt,
                                 Address address,
                                 Employer employer,
                                 Contacts contacts,
                                 String idVacancy) {
        return new AutoValue_Vacancy(salary, name, description, createdAt, address, employer, contacts, idVacancy);
    }
}
