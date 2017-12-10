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

    private static final String ACTION_CREATE_ENTRY = "android.intent.action.ACTION_CREATE_ENTRY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyApplication.getApplicationComponent().inject(this);

        ActiveAndroid.initialize(this);

        Fragment fragment = new ListVacanciesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.container_fragment, fragment);
        }
        fragmentTransaction.commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_action_create_entry :
//                Intent intent = new Intent(ACTION_CREATE_ENTRY);
//                startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
