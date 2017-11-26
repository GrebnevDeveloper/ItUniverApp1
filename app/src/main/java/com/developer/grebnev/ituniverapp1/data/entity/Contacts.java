package com.developer.grebnev.ituniverapp1.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by Grebnev on 26.11.2017.
 */
@AutoValue
public abstract class Contacts implements Parcelable {
    @Nullable public abstract String email();
    @Nullable public abstract List<Phone> phones();

    public static Contacts create(String email, List<Phone> phones) {
        return new AutoValue_Contacts(email, phones);
    }
}
