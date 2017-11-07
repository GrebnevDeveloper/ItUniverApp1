package com.developer.grebnev.ituniverapp1.domain.repository;

import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class DequeVacancies {
    @Inject
    public DequeVacancies() {}

    private Deque<Map<Integer, List<Vacancy>>> dequeVacancies = new ArrayDeque<>(2);
    private int oldRoute = EndlessRecyclerConstants.SCROLL_DOWN;

    public Deque<Map<Integer, List<Vacancy>>> getDequeVacancies() {
        return dequeVacancies;
    }

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
}
