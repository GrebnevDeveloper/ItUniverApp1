package com.developer.grebnev.ituniverapp1.data.local;

import com.activeandroid.query.Select;
import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 18.10.2017.
 */
@Singleton
public class DataQuery implements DataQueryInterface {
    @Inject
    public DataQuery() {
    }

    @Override
    public Observable<List<VacancyLocal>> getListVacancies(int start, int end) {
        return Observable.fromCallable(() ->
                new Select()
                        .from(VacancyLocal.class)
                        .where("ROWID > ? and ROWID < ?", start, end)
                        .execute()
        );
    }

    @Override
    public Observable<Integer> getCountVacancies() {
        return Observable.fromCallable(() ->
                new Select()
                        .from(VacancyLocal.class)
                        .count()
        );
    }
}
