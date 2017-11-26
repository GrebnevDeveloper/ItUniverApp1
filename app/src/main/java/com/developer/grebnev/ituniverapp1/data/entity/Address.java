package com.developer.grebnev.ituniverapp1.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

/**
 * Created by Grebnev on 02.11.2017.
 */
@AutoValue
public abstract class Address implements Parcelable {
    @Nullable public abstract String building();
    @Nullable public abstract String city();
    @Nullable public abstract String street();

    public static Address create(String building, String city, String street) {
        return new AutoValue_Address(building, city, street);
    }
}
