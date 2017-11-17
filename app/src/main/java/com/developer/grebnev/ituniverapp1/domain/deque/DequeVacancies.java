package com.developer.grebnev.ituniverapp1.domain.deque;

import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

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
    @Inject
    public DequeVacancies() {}

    private Deque<Map<Integer, List<Vacancy>>> dequeVacancies = new ArrayDeque<>(2);
    private int oldRoute = EndlessRecyclerConstants.SCROLL_DOWN;
    private Map<Integer, Long> mapTime = new HashMap<>();

    @Override
    public Deque<Map<Integer, List<Vacancy>>> getDequeVacancies() {
        return dequeVacancies;
    }

    @Override
    public void addElementIntoDeque(Map<Integer, List<Vacancy>> vacancies, int route) {
        if (dequeVacancies.isEmpty()) {
            dequeVacancies.push(new HashMap<Integer, List<Vacancy>>());
            dequeVacancies.push(new HashMap<Integer, List<Vacancy>>());
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
    }

    @Override
    public Vacancy getVacancyOfDeque(int position) {
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
}