package com.developer.grebnev.ituniverapp1.data;

import com.activeandroid.query.Select;
import com.developer.grebnev.ituniverapp1.mvp.models.Vacancy;

import java.util.List;

/**
 * Created by Grebnev on 18.10.2017.
 */

public class DatabaseQuery {
    public List<Vacancy> getListVacancies(int start, int end) {
        return new Select()
                .from(Vacancy.class)
                .where("ROWID > ? and ROWID < ?", start, end)
                .execute();
    }
    public int getCountVacancies() {
        return new Select()
                .from(Vacancy.class)
                .count();
    }
}
