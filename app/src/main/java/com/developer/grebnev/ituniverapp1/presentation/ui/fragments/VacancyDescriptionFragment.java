package com.developer.grebnev.ituniverapp1.presentation.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.presentation.mvp.presenters.VacancyDescriptionPresenter;
import com.developer.grebnev.ituniverapp1.presentation.mvp.view.VacancyDescriptionView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class VacancyDescriptionFragment extends MvpAppCompatFragment implements VacancyDescriptionView, View.OnClickListener {

    @InjectPresenter
    VacancyDescriptionPresenter vacancyDescriptionPresenter;

    @BindView(R.id.text_date_published)
    TextView tvDatePublished;
    @BindView(R.id.text_name_vacancy)
    TextView tvNameVacancy;
    @BindView(R.id.text_salary)
    TextView tvSalary;
    @BindView(R.id.text_name_employer)
    TextView tvNameEmployer;
    @BindView(R.id.text_location)
    TextView tvLocation;
    @BindView(R.id.text_description_vacancy)
    TextView tvDescriptionVacancy;
    @BindView(R.id.text_address_vacancy)
    TextView tvAddressVacancy;
    @BindView(R.id.text_phone)
    TextView tvPhone;
    @BindView(R.id.text_email)
    TextView tvEmail;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_vacancy_description, container, false);
        ButterKnife.bind(this, relativeLayout);
        showDataFromDeque();
        return relativeLayout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_phone :
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
                callIntent.setData(Uri.parse("tel:" + tvPhone.getText().toString()));
                startActivity(callIntent);
                break;
            case R.id.text_email :
                Intent emailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"));
                emailIntent.setData(Uri.parse("mailto:" + tvEmail.getText().toString()));
                Intent emailChooser = Intent.createChooser(emailIntent, "Email");
                startActivity(emailChooser);
                break;
        }
    }

    @Override
    public void showDataFromDeque() {
        Vacancy vacancy = getArguments().getParcelable(ListVacanciesFragment.KEY_VACANCY);
        tvNameVacancy.setText(vacancy.name());
        if (vacancy.salary() != null) {
            tvSalary.setText("from " + vacancy.salary().from() + " to " + vacancy.salary().to() + " " +
                    vacancy.salary().currency());
        }
        tvDatePublished.setText(vacancy.createdAt().substring(0, 10) + " in " + vacancy.createdAt().substring(12, 16));
        tvNameEmployer.setText(vacancy.employer().name());
        if (vacancy.address() != null) {
            tvLocation.setText(vacancy.address().city() + ", " + vacancy.address().street() + ", " + vacancy.address().building());
        }
        vacancyDescriptionPresenter.loadVacancyDescription(vacancy.idVacancy());

    }

    @Override
    public void showProgressLoad(int visibility) {
        if (visibility == View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showFullData(Vacancy vacancy) {
        tvDescriptionVacancy.setText(Html.fromHtml(vacancy.description()));
        if (vacancy.contacts() != null) {
            tvEmail.setText(vacancy.contacts().email());
            tvPhone.setText(vacancy.contacts().phones().get(0).country() +
                    vacancy.contacts().phones().get(0).city() + vacancy.contacts().phones().get(0).number());
            tvPhone.setOnClickListener(this);
            tvEmail.setOnClickListener(this);
        }
    }

    @Override
    public void showErrorConnection() {
        Snackbar.make(getView(), R.string.no_connection, Snackbar.LENGTH_LONG).show();
    }
}
