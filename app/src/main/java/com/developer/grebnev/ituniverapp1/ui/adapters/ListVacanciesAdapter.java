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
import com.developer.grebnev.ituniverapp1.mvp.models.DequeVacancies;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Grebnev on 11.10.2017.
 */

public class ListVacanciesAdapter extends RecyclerView.Adapter<ListVacanciesAdapter.ViewHolder> {

    private static final String TAG = ListVacanciesAdapter.class.getSimpleName();
    private Listener listener;
    private DequeVacancies dequeVacancies = new DequeVacancies();
    private DatabaseQuery query = new DatabaseQuery();
    private int countVacancies = query.getCountVacancies();

    public interface Listener {
        void onClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacancies, parent, false);
        final ViewHolder holder = new ViewHolder(cv);
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
        if (!dequeVacancies.getDequeVacancies().isEmpty()) {
            holder.tvNameVacancy.setText(dequeVacancies.getVacancyOfDeque(position).getName());
            holder.tvAddressVacancy.setText(dequeVacancies.getVacancyOfDeque(position).getAddressVacancy());
        }

        Log.d(TAG, "Bind view holder " + position);
    }

    @Override
    public int getItemCount() {
        return countVacancies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name_vacancy)
        TextView tvNameVacancy;
        @BindView(R.id.text_address_vacancy)
        TextView tvAddressVacancy;

        public ViewHolder(CardView cv) {
            super(cv);
            ButterKnife.bind(this, cv);
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setListVacancies(DequeVacancies vacancies) {
        this.dequeVacancies = vacancies;
    }
}
