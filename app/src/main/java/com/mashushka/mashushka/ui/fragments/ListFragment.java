package com.mashushka.mashushka.ui.fragments;

import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DB;
import com.mashushka.mashushka.ui.adapters.CountersListAdapter;
import com.mashushka.mashushka.viewmodel.CounterViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    @BindView(android.R.id.list) RecyclerView list;

    CountersListAdapter adapter = null;
    List<CounterEntity> data = new ArrayList<>();

    private CounterViewModel mCounterViewModel;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCounterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        mCounterViewModel.initObservers(getContext(), this);
        mCounterViewModel.getCounters().observe(this, new Observer<List<CounterEntity>>() {
            @Override
            public void onChanged(@Nullable List<CounterEntity> entities) {
                data = entities;
                if(adapter == null) {
                    adapter = new CountersListAdapter(getActivity(), data);
                    list.setAdapter(adapter);
                }

                adapter.setCountersList(data);
                adapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(llm);
        list.setAdapter(adapter);
    }

    public void onButtonPressed(Uri uri) {

    }

}
