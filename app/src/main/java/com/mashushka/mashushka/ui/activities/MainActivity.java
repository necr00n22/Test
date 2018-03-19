package com.mashushka.mashushka.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Counter;
import com.mashushka.mashushka.ui.fragments.CounterFragment;
import com.mashushka.mashushka.ui.fragments.CreateCounterFragment;
import com.mashushka.mashushka.ui.fragments.ListFragment;
import com.mashushka.mashushka.ui.listeners.CounterOpener;
import com.mashushka.mashushka.ui.listeners.OnCounterCreatedListener;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        OnCounterCreatedListener,
        ListFragment.CreateCounterListener,
        CounterOpener{

    private android.support.v4.app.FragmentManager mFragmentManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getString(R.string.app_name));

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance(this, this))
                .commit();
    }
    @Override
    public void createCounter() {
        mFragmentManager.beginTransaction()
        .replace(R.id.container, CreateCounterFragment.newInstance(this))
                .addToBackStack(null)
        .commit();
    }

    @Override
    public void onCounterCreated(Counter counter) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showCounter(long id) {
        mFragmentManager.beginTransaction()
                .replace(R.id.container, CounterFragment.newInstance(id))
                .addToBackStack(null)
                .commit();
    }
}
