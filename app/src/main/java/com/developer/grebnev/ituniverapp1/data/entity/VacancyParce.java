package com.developer.grebnev.ituniverapp1.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

/**
 * Created by Grebnev on 17.11.2017.
 */

@AutoValue
public abstract class VacancyParce implements Parcelable {
    public abstract ArrayList<Vacancy> description();

    public static VacancyParce create(ArrayList<Vacancy> description) {
        return new AutoValue_VacancyParce(description);
    }
}
