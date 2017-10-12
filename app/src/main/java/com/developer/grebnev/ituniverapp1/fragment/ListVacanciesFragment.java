package com.developer.grebnev.ituniverapp1.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.adapter.ListVacanciesAdapter;

/**
 * Created by Grebnev on 11.10.2017.
 */

public class ListVacanciesFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_vacancies, container, false);

        RecyclerView listVacanciesRecycler = (RecyclerView)view.findViewById(R.id.list_vacancies_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listVacanciesRecycler.setLayoutManager(layoutManager);
        ListVacanciesAdapter adapter = new ListVacanciesAdapter();
        listVacanciesRecycler.setAdapter(adapter);
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

        return view;
    }
}
