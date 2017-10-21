package com.developer.grebnev.ituniverapp1.mvp.models;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * Created by Grebnev on 18.10.2017.
 */

public class DequeVacancies {
    private Deque<Map<Integer, List<Vacancy>>> dequeVacancies = new ArrayDeque<>(2);

    public Deque<Map<Integer, List<Vacancy>>> getDequeVacancies() {
        return dequeVacancies;
    }

    public void addElementIntoDeque(Map<Integer, List<Vacancy>> vacancies, int route) {
        if (route == 1) {
            dequeVacancies.addLast(vacancies);
        }
        else {
            if (route == -1) {
                dequeVacancies.addFirst(vacancies);
            }
        }
    }
}
