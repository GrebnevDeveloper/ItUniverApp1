package com.developer.grebnev.ituniverapp1.presentation.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.developer.grebnev.ituniverapp1.MyApplication;
import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.domain.repository.DequeVacancies;
import com.developer.grebnev.ituniverapp1.presentation.mvp.presenters.ListVacanciesPresenter;
import com.developer.grebnev.ituniverapp1.presentation.mvp.view.ListVacanciesView;
import com.developer.grebnev.ituniverapp1.presentation.ui.adapters.ListVacanciesAdapter;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class ListVacanciesFragment extends MvpAppCompatFragment implements ListVacanciesView {

    private static final String TAG = ListVacanciesFragment.class.getSimpleName();

    public static final String KEY_VACANCY = "vacancy";

    @BindView(R.id.recycler_list_vacancies)
    RecyclerView listVacanciesRecycler;

    ProgressDialog progressDialog;

    @InjectPresenter
    ListVacanciesPresenter listVacanciesPresenter;

    ListVacanciesAdapter adapter;

    private int totalItemCountFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_vacancies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        createProgressDialog();
        totalItemCountFragment = listVacanciesPresenter.getTotalItemCountPresenter();
        setUpRecyclerView();
        setUpAdapter(listVacanciesRecycler);
        listVacanciesPresenter.loadNextDataFromDatabase(totalItemCountFragment);
    }

    public static VacancyDescriptionFragment newInstance(Vacancy vacancy) {
        Bundle args = new Bundle();
        //args.putParcelable(KEY_VACANCY, vacancy);
        VacancyDescriptionFragment fragment = new VacancyDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showProgressLoad(int visibility) {
        if (visibility == View.VISIBLE) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showListVacancies(DequeVacancies vacancies) {
        adapter.setListVacancies(vacancies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorConnection() {
        Snackbar.make(getView(), R.string.no_connection, Snackbar.LENGTH_LONG).show();
    }

    private void setUpRecyclerView() {
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

    private void setUpAdapter(RecyclerView rv) {
        adapter = new ListVacanciesAdapter();
        rv.setAdapter(adapter);
        adapter.setListener(new ListVacanciesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Fragment fragment =
                        ListVacanciesFragment.newInstance(listVacanciesPresenter.getDequeVacancies().getVacancyOfDeque(position));
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void createProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.uploading_data));
    }
}
