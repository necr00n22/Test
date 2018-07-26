package com.mashushka.mashushka.ui.fragments;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.mashushka.mashushka.ui.adapters.CounterAdapter;
import com.mashushka.mashushka.viewmodel.CounterViewModel;

import java.util.ArrayList;
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

    @BindView(R.id.list)
    RecyclerView list;

    private CounterViewModel mCountersListViewModel;
    private long id = -1;
    private CounterAdapter adapter;

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

    private void deleteCounter() {
        if(id != -1)
            DataRepository.getInstance(getActivity()).deleteSingleCounter(id);
        getActivity().onBackPressed();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getLong(ID, -1L);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new CounterAdapter(getContext(), new ArrayList<>());
        list.setLayoutManager(llm);
        list.setAdapter(adapter);

        mCountersListViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        mCountersListViewModel.getCounterById(id).observe(this, new Observer<CounterEntity>() {
            @Override
            public void onChanged(@Nullable CounterEntity counterEntity) {
                adapter.setItems(counterEntity.getBlocks());
            }
        });
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
}
