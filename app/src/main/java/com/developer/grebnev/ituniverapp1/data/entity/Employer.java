package com.developer.grebnev.ituniverapp1.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

/**
 * Created by Grebnev on 26.11.2017.
 */
@AutoValue
public abstract class Employer implements Parcelable {
    @Nullable public abstract String name();

    public static Employer create(String name) {
        return new AutoValue_Employer(name);
    }
}
