package com.mashushka.mashushka.ui.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Counter;
import com.mashushka.mashushka.ui.fragments.CreateCounterFragment;
import com.mashushka.mashushka.ui.fragments.ListFragment;
import com.mashushka.mashushka.ui.listeners.OnCounterCreatedListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnCounterCreatedListener, ListFragment.CreateCounterListener {

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
                .replace(R.id.container, ListFragment.newInstance(this))
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
}
