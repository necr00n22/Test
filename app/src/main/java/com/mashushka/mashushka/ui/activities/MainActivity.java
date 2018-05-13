package com.mashushka.mashushka.ui.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Counter;
import com.mashushka.mashushka.ui.fragments.CounterFragment;
import com.mashushka.mashushka.ui.fragments.ListFragment;
import com.mashushka.mashushka.ui.listeners.CounterOpener;
import com.mashushka.mashushka.ui.listeners.OnCounterCreatedListener;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        OnCounterCreatedListener,
        CounterOpener{

    private android.support.v4.app.FragmentManager mFragmentManager;

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
                .replace(R.id.container, ListFragment.newInstance())
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
                .setCustomAnimations(R.animator.slide_in_right, R.animator.slide_in_left)
                .replace(R.id.container, CounterFragment.newInstance(id))
                .addToBackStack(null)
                .commit();
    }
}
