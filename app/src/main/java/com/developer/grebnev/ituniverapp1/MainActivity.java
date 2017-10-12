package com.developer.grebnev.ituniverapp1;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.developer.grebnev.ituniverapp1.fragment.ListVacanciesFragment;

public class MainActivity extends AppCompatActivity {

    static final String ACTION_CREATE_ENTRY = "android.intent.action.ACTION_CREATE_ENTRY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new ListVacanciesFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
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
}
