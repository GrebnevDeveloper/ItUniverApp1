package com.developer.grebnev.ituniverapp1.domain.interactor;

import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacanciesLoader;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class DequeVacanciesInteractor implements DequeIteractorInterface{
    DequeVacanciesLoader dequeVacanciesRepository;

    @Inject
    public DequeVacanciesInteractor(DequeVacanciesLoader dequeVacanciesRepository) {
        this.dequeVacanciesRepository = dequeVacanciesRepository;
    }

    private DequeVacancies dequeVacancies = new DequeVacancies();
    private int totalItemCountPresenter = EndlessRecyclerConstants.VOLUME_LOAD;
    private String textSearch = "";

    @Override
    public Observable<DequeVacancies> getDequeVacancies(int totalItemCountPresenter, int route) {
        return dequeVacanciesRepository.loadVacancies(textSearch ,totalItemCountPresenter, route);
    }

    @Override
    public boolean isInternetConnection() {
        return dequeVacanciesRepository.isInternetConnection();
    }

    @Override
    public int getTotalItemCountPresenter() {
        return totalItemCountPresenter;
    }

    @Override
    public void setTotalItemCountPresenter(int countPresenter) {
        this.totalItemCountPresenter = countPresenter;
    }

    @Override
    public DequeVacancies getDequeVacancies() {
        return dequeVacancies;
    }

    @Override
    public void setDequeVacancies(DequeVacancies dequeVacancies) {
        this.dequeVacancies = dequeVacancies;
    }

    @Override
    public int getScrollConstants(int totalItemCount) {
        if (totalItemCountPresenter == totalItemCount) {
            if (!dequeVacancies.getDequeVacancies().isEmpty()) {
                return EndlessRecyclerConstants.SCROLL_NO;
            } else {
                return EndlessRecyclerConstants.SCROLL_DOWN;
            }
        } else {
            if (totalItemCountPresenter > totalItemCount) {
                totalItemCountPresenter = totalItemCount;
                return EndlessRecyclerConstants.SCROLL_UP;
            } else {
                totalItemCountPresenter = totalItemCount;
                return EndlessRecyclerConstants.SCROLL_DOWN;
            }
        }
    }

    @Override
    public String getTextSearch() {
        return textSearch;
    }

    @Override
    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }
}
