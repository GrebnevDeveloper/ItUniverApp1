package com.developer.grebnev.ituniverapp1.domain.deque;

import com.developer.grebnev.ituniverapp1.presentation.mvp.model.VacancyPresentation;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Grebnev on 12.11.2017.
 */

public class DequeVacancies implements DequeVacanciesInterface {
    private Deque<Map<Integer, List<VacancyPresentation>>> dequeVacancies = new ArrayDeque<>(2);
    private int oldRoute = EndlessRecyclerConstants.SCROLL_DOWN;
    private Map<Integer, Long> mapTime = new HashMap<>();
    private String oldTextSearch = "";
    private int itemCount = 0;

    @Inject
    public DequeVacancies() {
    }

    @Override
    public Deque<Map<Integer, List<VacancyPresentation>>> getDequeVacancies() {
        return dequeVacancies;
    }

    @Override
    public void addElementIntoDeque(Map<Integer, List<VacancyPresentation>> vacancies, int route) {
        if (route == EndlessRecyclerConstants.SCROLL_NO) {
            route = EndlessRecyclerConstants.SCROLL_DOWN;
            oldRoute = EndlessRecyclerConstants.SCROLL_DOWN;
            dequeVacancies = new ArrayDeque<>(2);
        }
        if (dequeVacancies.isEmpty()) {
            dequeVacancies.push(new HashMap<Integer, List<VacancyPresentation>>());
            dequeVacancies.push(new HashMap<Integer, List<VacancyPresentation>>());
        }
        if (route == EndlessRecyclerConstants.SCROLL_DOWN && oldRoute == EndlessRecyclerConstants.SCROLL_DOWN) {
            dequeVacancies.pollFirst();
            dequeVacancies.addLast(vacancies);
        } else {
            if (route == EndlessRecyclerConstants.SCROLL_UP && oldRoute == EndlessRecyclerConstants.SCROLL_UP) {
                dequeVacancies.pollLast();
                dequeVacancies.addFirst(vacancies);
            }
        }
        oldRoute = route;
        for (Integer key : dequeVacancies.getLast().keySet()) {
            if (itemCount < key * EndlessRecyclerConstants.VOLUME_LOAD) {
                itemCount = key * EndlessRecyclerConstants.VOLUME_LOAD;
            }
        }
    }

    @Override
    public VacancyPresentation getVacancyOfDeque(int position) {
        if (dequeVacancies.getFirst().containsKey(position / EndlessRecyclerConstants.VOLUME_LOAD + 1)) {
            return dequeVacancies
                    .getFirst()
                    .get(position / EndlessRecyclerConstants.VOLUME_LOAD + 1)
                    .get(position % EndlessRecyclerConstants.VOLUME_LOAD);
        }
        if (dequeVacancies.getLast().containsKey(position / EndlessRecyclerConstants.VOLUME_LOAD + 1)) {
            return dequeVacancies
                    .getLast()
                    .get(position / EndlessRecyclerConstants.VOLUME_LOAD + 1)
                    .get(position % EndlessRecyclerConstants.VOLUME_LOAD);
        }
        return null;
    }

    @Override
    public void writeTime(int page, long time) {
        mapTime.put(page, time);
    }

    @Override
    public Map<Integer, Long> getMapTime() {
        return mapTime;
    }

    @Override
    public String getOldTextSearch() {
        return oldTextSearch;
    }

    @Override
    public void setOldTextSearch(String textSearch) {
        this.oldTextSearch = textSearch;
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}
