package com.developer.grebnev.ituniverapp1.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.mvp.models.EndlessRecyclerScrollListener;
import com.developer.grebnev.ituniverapp1.mvp.models.Vacancy;
import com.developer.grebnev.ituniverapp1.mvp.presenters.ListVacanciesPresenter;
import com.developer.grebnev.ituniverapp1.mvp.views.ListVacanciesView;
import com.developer.grebnev.ituniverapp1.ui.adapters.ListVacanciesAdapter;

import java.util.Deque;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Grebnev on 11.10.2017.
 */

public class ListVacanciesFragment extends MvpAppCompatFragment implements ListVacanciesView {

    @BindView(R.id.recycler_list_vacancies)
    RecyclerView listVacanciesRecycler;

    @InjectPresenter
    ListVacanciesPresenter listVacanciesPresenter;

    ListVacanciesAdapter adapter;

    private int totalItemCountFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_vacancies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        totalItemCountFragment = listVacanciesPresenter.getTotalItemCountPresenter();
        setUpRecyclerView();
        setUpAdapter(listVacanciesRecycler);
        listVacanciesPresenter.loadNextDataFromDatabase(totalItemCountFragment);
    }

    @Override
    public void showListVacancies(Deque<Map<Integer, List<Vacancy>>> vacancies) {
        adapter.setListVacancies(vacancies);
    }

    public void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listVacanciesRecycler.setLayoutManager(layoutManager);
        listVacanciesRecycler.addOnScrollListener(new EndlessRecyclerScrollListener(layoutManager, totalItemCountFragment) {
            @Override
            public void onLoadMore(int totalItemCount) {
                totalItemCountFragment = totalItemCount;
                listVacanciesPresenter.loadNextDataFromDatabase(totalItemCount);
            }
        });
    }

    public void setUpAdapter(RecyclerView rv) {
        adapter = new ListVacanciesAdapter();
        rv.setAdapter(adapter);
        adapter.setListener(new ListVacanciesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Fragment fragment = new VacancyDescriptionFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
