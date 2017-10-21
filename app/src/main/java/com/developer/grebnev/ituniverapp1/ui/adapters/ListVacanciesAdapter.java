package com.developer.grebnev.ituniverapp1.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.data.DatabaseQuery;
import com.developer.grebnev.ituniverapp1.mvp.models.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grebnev on 11.10.2017.
 */

public class ListVacanciesAdapter extends RecyclerView.Adapter<ListVacanciesAdapter.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private Listener listener;
    private List<Vacancy> vacancies = new ArrayList<>();
    DatabaseQuery queryDB = new DatabaseQuery();
    private int countVacancies = queryDB.getCountVacancies();

    public interface Listener {
        void onClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacancies, parent, false);
        final ViewHolder holder = new ViewHolder(cv) {};
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onClick(adapterPosition);
                    }
                }
            }
        });
        Log.d(TAG, "Create view holder");
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CardView cv = holder.cardVacancies;
        TextView tvTestText = (TextView) cv.findViewById(R.id.text_test);
        tvTestText.setText(vacancies.get(position % 100).getName());
        Log.d(TAG, "Bind view holder " + position);
    }

    @Override
    public int getItemCount() {
        return countVacancies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardVacancies;

        public ViewHolder(CardView cv) {
            super(cv);
            cardVacancies = cv;
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setListVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
