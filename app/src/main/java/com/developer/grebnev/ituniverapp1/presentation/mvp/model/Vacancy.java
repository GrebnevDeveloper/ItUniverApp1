package com.developer.grebnev.ituniverapp1.presentation.mvp.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by Grebnev on 07.12.2017.
 */
@AutoValue
public abstract class Vacancy implements Parcelable {
    public abstract String salary();

    public abstract String name();

    @Nullable
    public abstract String description();

    public abstract String createdAt();

    public abstract String address();

    public abstract String employer();

    public abstract String email();

    public abstract String phone();

    public abstract String idVacancy();

    public static Vacancy create(String salary,
                                 String name,
                                 String description,
                                 String createdAt,
                                 String address,
                                 String employer,
                                 String email,
                                 String phone,
                                 String idVacancy) {
        return new AutoValue_Vacancy(salary, name, description, createdAt, address, employer, email, phone, idVacancy);
    }
}
