package com.developer.grebnev.ituniverapp1.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.data.DatabaseQuery;
import com.developer.grebnev.ituniverapp1.mvp.models.Vacancy;
import com.developer.grebnev.ituniverapp1.mvp.views.ListVacanciesView;

import java.util.List;

/**
 * Created by Grebnev on 20.10.2017.
 */

@InjectViewState
public class ListVacanciesPresenter extends MvpPresenter<ListVacanciesView> {
    private int totalItemCountPresenter = 100;

    public int getTotalItemCountPresenter() {
        return totalItemCountPresenter;
    }

    public void loadNextDataFromDatabase(int totalItemCount) {
        totalItemCountPresenter = totalItemCount;
        DatabaseQuery query = new DatabaseQuery();
        List<Vacancy> vacancies = query.getListVacancies(totalItemCountPresenter - 100, totalItemCountPresenter + 1);
        getViewState().showListVacancies(vacancies);
    }

}
