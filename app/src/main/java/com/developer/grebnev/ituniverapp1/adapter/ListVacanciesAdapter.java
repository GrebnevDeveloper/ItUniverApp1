package com.developer.grebnev.ituniverapp1.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.grebnev.ituniverapp1.R;

/**
 * Created by Grebnev on 11.10.2017.
 */

public class ListVacanciesAdapter extends RecyclerView.Adapter<ListVacanciesAdapter.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacancies, parent, false);
        Log.d(TAG, "Create view holder");
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CardView cv = holder.cardVacancies;
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
        Log.d(TAG, "Bind view holder " + position);
    }

    @Override
    public int getItemCount() {
        return 1000;
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
}
