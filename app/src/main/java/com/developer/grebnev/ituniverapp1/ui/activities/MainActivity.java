package com.developer.grebnev.ituniverapp1.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.ActiveAndroid;
import com.developer.grebnev.ituniverapp1.App;
import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.di.components.ApplicationComponent;
import com.developer.grebnev.ituniverapp1.di.components.VacancyComponent;
import com.developer.grebnev.ituniverapp1.di.modules.ActivityModule;
import com.developer.grebnev.ituniverapp1.ui.fragments.ListVacanciesFragment;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_CREATE_ENTRY = "android.intent.action.ACTION_CREATE_ENTRY";

    private VacancyComponent vacancyComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.getApplicationComponent().inject(this);

//        this.vacancyComponent = DaggerVacancyComponent.builder()
//                .applicationComponent(getApplicationComponent())
//                .activityModule(getActivityModule())
//                .build();

        ActiveAndroid.initialize(this);

        Fragment fragment = new ListVacanciesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.container_fragment, fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_create_entry :
                Intent intent = new Intent(ACTION_CREATE_ENTRY);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public VacancyComponent getComponent() {
        return vacancyComponent;
    }
}
