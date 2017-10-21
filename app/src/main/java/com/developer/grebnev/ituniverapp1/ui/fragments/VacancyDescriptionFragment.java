package com.developer.grebnev.ituniverapp1.ui.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developer.grebnev.ituniverapp1.R;

/**
 * Created by Grebnev on 11.10.2017.
 */

public class VacancyDescriptionFragment extends Fragment implements View.OnClickListener {

    private TextView tvPhone;
    private TextView tvEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_vacancy_description, container, false);
        tvPhone = (TextView) relativeLayout.findViewById(R.id.text_phone);
        tvEmail = (TextView) relativeLayout.findViewById(R.id.text_email);
        tvPhone.setOnClickListener(this);
        tvEmail.setOnClickListener(this);

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
}
