package com.developer.grebnev.ituniverapp1.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.developer.grebnev.ituniverapp1.mvp.models.Vacancy;

import java.util.List;

/**
 * Created by Grebnev on 20.10.2017.
 */

@StateStrategyType(SingleStateStrategy.class)
public interface ListVacanciesView extends MvpView {
    void showListVacancies(List<Vacancy> vacancies);
}
