package com.mashushka.mashushka.ui.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.ui.listeners.CounterOpener;
import com.mashushka.mashushka.viewmodel.CounterViewModel;
import com.mashushka.mashushka.viewmodel.CountersListViewModel;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CounterFragment extends Fragment {

    private static final String ID = "id";

    @BindView(R.id.days) TextView days;
    @BindView(R.id.hours_mins_secs) TextView hoursMinsSecs;
    @BindView(R.id.description) TextView description;

    private CounterViewModel mCountersListViewModel;
    private long id;

    public CounterFragment() {
        // Required empty public constructor
    }


    public static CounterFragment newInstance(long id) {
        CounterFragment fragment = new CounterFragment();
        Bundle args = new Bundle();
        args.putLong(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getLong(ID, -1L);

        mCountersListViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        mCountersListViewModel.getCounterById(id).observe(this, new Observer<CounterEntity>() {
            @Override
            public void onChanged(@Nullable CounterEntity counterEntity) {

                Date timePassed = new Date();
                timePassed.setTime(new Date().getTime() - counterEntity.getCreateDate());

                long time = timePassed.getTime() / 1000;

                long d = time / (3600 * 24);
                long h = (time - (d * (3600 * 24))) / (3600);
                long m = (time - (d * (3600 * 24)) - (h * 3600)) / (60);
                long s =  (time - d * (3600 * 24) -  h * 3600 - m  * 60);

                days.setText(d + getString(R.string.days));
                hoursMinsSecs.setText(
                        h + getString(R.string.hours) + " " +
                        m + getString(R.string.minutes) + " " +
                        s + getString(R.string.seconds));
                description.setText(counterEntity.getTitle());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
