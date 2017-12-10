package com.developer.grebnev.ituniverapp1.presentation.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.activeandroid.ActiveAndroid;
import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.presentation.ui.fragments.ListVacanciesFragment;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActiveAndroid.initialize(this);

        Fragment fragment = new ListVacanciesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.container_fragment, fragment);
        }
        fragmentTransaction.commit();
    }
}
