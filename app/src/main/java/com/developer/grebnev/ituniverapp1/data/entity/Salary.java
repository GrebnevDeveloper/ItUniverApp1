package com.developer.grebnev.ituniverapp1.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

/**
 * Created by Grebnev on 26.11.2017.
 */

@AutoValue
public abstract class Salary implements Parcelable {
    @Nullable public abstract Integer to();
    @Nullable public abstract Integer from();
    @Nullable public abstract String currency();

    public static Salary create(Integer to, Integer from, String currency) {
        return new AutoValue_Salary(to, from, currency);
    }
}
