package com.developer.grebnev.ituniverapp1.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

/**
 * Created by Grebnev on 26.11.2017.
 */
@AutoValue
public abstract class Phone implements Parcelable {
    @Nullable public abstract String country();
    @Nullable public abstract String city();
    @Nullable public abstract String number();

    public static Phone create(String country, String city, String number) {
        return new AutoValue_Phone(country, city, number);
    }
}
