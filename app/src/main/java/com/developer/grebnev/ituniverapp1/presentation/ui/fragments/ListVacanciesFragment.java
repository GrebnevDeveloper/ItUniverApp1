package com.developer.grebnev.ituniverapp1.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.di.ComponentManager;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;
import com.developer.grebnev.ituniverapp1.presentation.mvp.model.VacancyPresentation;
import com.developer.grebnev.ituniverapp1.presentation.mvp.presenters.ListVacanciesPresenter;
import com.developer.grebnev.ituniverapp1.presentation.mvp.view.ListVacanciesView;
import com.developer.grebnev.ituniverapp1.presentation.ui.activities.MainActivity;
import com.developer.grebnev.ituniverapp1.presentation.ui.adapters.ListVacanciesAdapter;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class ListVacanciesFragment extends MvpAppCompatFragment implements ListVacanciesView {

    public static final String KEY_VACANCY = "vacancy";
    private static final String TAG = ListVacanciesFragment.class.getSimpleName();
    @BindView(R.id.recycler_list_vacancies)
    RecyclerView listVacanciesRecycler;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    @InjectPresenter
    ListVacanciesPresenter listVacanciesPresenter;
    ListVacanciesAdapter adapter;
    private int totalItemCountFragment;

    public ListVacanciesFragment() {
    }

    public static VacancyDescriptionFragment newInstance(VacancyPresentation vacancy) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_VACANCY, vacancy);
        VacancyDescriptionFragment fragment = new VacancyDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    ListVacanciesPresenter providePresenter() {
        return listVacanciesPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getListVacanciesComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
        setUpSwipeToRefreshLayout(view);
        listVacanciesPresenter.loadNextDataFromDatabase(totalItemCountFragment);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_option_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);

        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listVacanciesPresenter.setTextSearch(query);
                loadNewData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("") && !listVacanciesPresenter.getTextSearch().equals("")) {
                    listVacanciesPresenter.setTextSearch(newText);
                    loadNewData();
                }
                return false;
            }
        });
    }

    @Override
    public void showProgressLoad(boolean visibility) {
        swipeRefreshLayout.setRefreshing(visibility);
    }

    @Override
    public void showListVacancies(DequeVacancies vacancies) {
        adapter.setListVacancies(vacancies);
        adapter.notifyDataSetChanged();
        showProgressLoad(false);
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

    private void setUpSwipeToRefreshLayout(View rootView) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNewData();
                Log.d(TAG, "Swipe refresh");
            }
        });
    }

    private void loadNewData() {
        listVacanciesPresenter.setTotalItemCountPresenter(100);
        totalItemCountFragment = listVacanciesPresenter.getTotalItemCountPresenter();
        listVacanciesPresenter.loadNextDataFromDatabase(totalItemCountFragment);
    }
}
