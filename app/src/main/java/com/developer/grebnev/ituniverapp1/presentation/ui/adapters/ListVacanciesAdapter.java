package com.developer.grebnev.ituniverapp1.presentation.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class ListVacanciesAdapter extends RecyclerView.Adapter<ListVacanciesAdapter.ViewHolder> {

    private static final String TAG = ListVacanciesAdapter.class.getSimpleName();
    private Listener listener;
    private DequeVacancies dequeVacancies = new DequeVacancies();

    public interface Listener {
        void onClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacancies, parent, false);
        final ListVacanciesAdapter.ViewHolder holder = new ListVacanciesAdapter.ViewHolder(cv);
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
    public void onBindViewHolder(final ListVacanciesAdapter.ViewHolder holder, final int position) {
        if (!dequeVacancies.getDequeVacancies().isEmpty()) {
            holder.tvNameVacancy.setText(dequeVacancies.getVacancyOfDeque(position).name());
            holder.tvSalaryVacancy.setText(dequeVacancies.getVacancyOfDeque(position).salary());
            holder.tvEmployerVacancy.setText(dequeVacancies.getVacancyOfDeque(position).employer());
            holder.tvAddressVacancy.setText(dequeVacancies.getVacancyOfDeque(position).address());
            holder.tvDateCreatedVacancy.setText(dequeVacancies.getVacancyOfDeque(position).createdAt());
        }
        Log.d(TAG, "Bind view holder " + position);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "Count from deque " + dequeVacancies.getItemCount());
        return dequeVacancies.getItemCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name_vacancy)
        TextView tvNameVacancy;
        @BindView(R.id.text_salary_vacancy)
        TextView tvSalaryVacancy;
        @BindView(R.id.text_employer_vacancy)
        TextView tvEmployerVacancy;
        @BindView(R.id.text_address_vacancy)
        TextView tvAddressVacancy;
        @BindView(R.id.text_date_created_vacancy)
        TextView tvDateCreatedVacancy;

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
