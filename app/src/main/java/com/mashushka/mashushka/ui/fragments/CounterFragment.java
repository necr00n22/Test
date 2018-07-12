package com.mashushka.mashushka.ui.fragments;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;
import com.mashushka.mashushka.ui.CircleProgressBar;
import com.mashushka.mashushka.viewmodel.CounterViewModel;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
    @BindView(R.id.tv_reset_count) TextView tvResetCount;
    @BindView(R.id.tv_reset_max) TextView tvResetMax;
    @BindView(R.id.custom_progressBar)
    CircleProgressBar circleProgressBar;

    private CounterViewModel mCountersListViewModel;
    private long id = -1;
    private TimerTask task;
    private Timer timer;
    private long previousProgress = 0;
    private CounterEntity counter;

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.counter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                showDeleteCounterDialog();
                return true;
            case R.id.reset:
//                showResetCounterDialog();
                return true;
        }

        return false;
    }

    private void showDeleteCounterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setMessage(R.string.dialog_delete_description);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteCounter();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

//    private void showResetCounterDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setCancelable(true);
//        builder.setMessage(R.string.dialog_reset_description);
//        builder.setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                resetCounter();
//            }
//        });
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        builder.show();
//    }

    private void deleteCounter() {
        if(id != -1)
            DataRepository.getInstance(getActivity()).deleteSingleCounter(id);
        getActivity().onBackPressed();
    }

//    private void resetCounter() {
//        if(id != -1){
//            if(counter.getMaxPeriod() < new Date().getTime() - counter.getStartDate())
//                counter.setMaxPeriod(new Date().getTime() - counter.getStartDate());
//            counter.setStartDate(new Date().getTime());
//            counter.setCounter(counter.getCounter() + 1);
//            DataRepository.getInstance(getActivity()).updateSingleCounter(counter);
//        }
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getLong(ID, -1L);

        mCountersListViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        mCountersListViewModel.getCounterById(id).observe(this, new Observer<CounterEntity>() {
            @Override
            public void onChanged(@Nullable CounterEntity counterEntity) {
                counter = counterEntity;
                description.setText(counter.getTitle());
//                tvResetCount.setText(String.valueOf(counter.getCounter()));
//                tvResetMax.setText(convertLongToTime(counter.getMaxPeriod()));
                int allDays = (int) ((counter.getEndDate() -  counter.getCreateDate()) / (1000 * 60 * 60 *24));
                circleProgressBar.setMax(allDays);
                setTimer();
            }
        });
    }

    private void setTimer() {
        Date timePassed = new Date();
        timePassed.setTime(new Date().getTime() - counter.getCreateDate());

        long time = timePassed.getTime() / 1000;

        long d = time / (3600 * 24);
        int daysPassed = (int) timePassed.getTime() / (1000 * 60 * 60 *24);
        days.setText(d + " " + getResources().getQuantityString(R.plurals.days, (int) d));
        circleProgressBar.setProgress(daysPassed);

    }

    private String convertLongToTime(long maxPeriod) {

        long time = maxPeriod / 1000;

        long d = time / (3600 * 24);
        long h = (time - (d * (3600 * 24))) / (3600);
        long m = (time - (d * (3600 * 24)) - (h * 3600)) / (60);
        long s =  (time - d * (3600 * 24) -  h * 3600 - m  * 60);


        return d + " " + getResources().getQuantityString(R.plurals.days, (int) d) + " " +
                timeFormat(h) + ":" +
                timeFormat(m) + ":" +
                timeFormat(s);
    }

    private String timeFormat(long time) {
        return String.valueOf(time).length() == 1 ? "0" + time : String.valueOf(time);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {

        task = new TimerTask() {
            @Override
            public void run() {
                setTimer();
            }
        };

        timer = new Timer();
        timer.schedule(task, 1000, 1000);
        super.onResume();
    }

    @Override
    public void onPause() {
        timer.purge();
        timer.cancel();
        super.onPause();
    }
}
