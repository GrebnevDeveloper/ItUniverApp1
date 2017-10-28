package com.developer.grebnev.ituniverapp1.mvp.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Grebnev on 18.10.2017.
 */
@Table(name = "Vacancy", id = "_id")
public class Vacancy extends Model implements Parcelable{
    @Column(name = "name")
    private String name;
    @Column(name = "addressVacancy")
    private String addressVacancy;
    private long idVacancy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressVacancy() {
        return addressVacancy;
    }

    public void setAddressVacancy(String addressVacancy) {
        this.addressVacancy = addressVacancy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(name);
        dest.writeString(addressVacancy);
    }

    public Vacancy() {
    }

    protected Vacancy(Parcel in) {
        idVacancy = in.readLong();
        name = in.readString();
        addressVacancy = in.readString();
    }

    public static final Creator<Vacancy> CREATOR = new Creator<Vacancy>() {
        @Override
        public Vacancy createFromParcel(Parcel in) {
            return new Vacancy(in);
        }

        @Override
        public Vacancy[] newArray(int size) {
            return new Vacancy[size];
        }
    };

}
