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

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        OnCounterCreatedListener{

    private FragmentManager mFragmentManager;

    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getString(R.string.app_name));

        mFragmentManager = getFragmentManager();
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                createCounter();
                break;
        }
    }

    private void createCounter() {
        mFragmentManager.beginTransaction()
        .replace(R.id.container, CreateCounterFragment.newInstance(this))
        .commit();
    }

    @Override
    public void onCounterCreated(Counter counter) {
        mFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commit();
    }
}
