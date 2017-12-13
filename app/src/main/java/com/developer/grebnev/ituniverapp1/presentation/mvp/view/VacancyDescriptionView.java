package com.developer.grebnev.ituniverapp1.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.developer.grebnev.ituniverapp1.presentation.mvp.model.Vacancy;

/**
 * Created by Grebnev on 25.11.2017.
 */
@StateStrategyType(SingleStateStrategy.class)
public interface VacancyDescriptionView extends MvpView {
    void showDataFromDeque();

    void showProgressLoad(boolean visibility);

    void showFullData(Vacancy vacancy);

    void showErrorConnection();
}
