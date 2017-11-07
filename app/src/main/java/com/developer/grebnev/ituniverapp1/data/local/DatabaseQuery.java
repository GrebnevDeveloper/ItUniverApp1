package com.developer.grebnev.ituniverapp1.data.local;

import com.activeandroid.query.Select;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 18.10.2017.
 */

public class DatabaseQuery {
    @Inject
    public DatabaseQuery() {}

    public Observable<List<Vacancy>> getListVacancies(int start, int end) {
        return Observable.just(new Select()
                .from(Vacancy.class)
                .where("ROWID > ? and ROWID < ?", start, end)
                .execute());
    }

    public int getCountVacancies() {
        return new Select()
                .from(Vacancy.class)
                .count();
    }
}
