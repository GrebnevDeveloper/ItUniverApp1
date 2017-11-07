package com.developer.grebnev.ituniverapp1.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.developer.grebnev.ituniverapp1.domain.repository.DequeVacancies;

/**
 * Created by Grebnev on 07.11.2017.
 */

@StateStrategyType(SingleStateStrategy.class)
public interface ListVacanciesView extends MvpView {
    void showProgressLoad(int visibility);
    void showListVacancies(DequeVacancies vacancies);
}