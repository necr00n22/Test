package com.test.test.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.test.test.R;
import com.test.test.ui.fragments.CounterFragment;
import com.test.test.ui.fragments.ListFragment;
import com.test.test.ui.listeners.CounterOpener;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        CounterOpener{

    private android.support.v4.app.FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commit();
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
                .setCustomAnimations(R.animator.slide_in_right, R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_out_right)
                .replace(R.id.container, CounterFragment.newInstance(id))
                .addToBackStack(null)
                .commit();
    }
}
