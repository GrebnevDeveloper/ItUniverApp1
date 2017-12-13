package com.developer.grebnev.ituniverapp1.presentation.mvp.presenters;

import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;

/**
 * Created by Grebnev on 12.11.2017.
 */

public interface ListPresenterInterface {
    void loadNextDataFromDatabase(int totalItemCount);

    void loadData(final int route);

    int getTotalItemCountPresenter();

    void setTotalItemCountPresenter(int countPresenter);

    DequeVacancies getDequeVacancies();

    String getTextSearch();

    void setTextSearch(String textSearch);
}
