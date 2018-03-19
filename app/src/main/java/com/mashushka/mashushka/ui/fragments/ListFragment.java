package com.mashushka.mashushka.ui.fragments;

import android.support.design.widget.FloatingActionButton;
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
import com.mashushka.mashushka.ui.adapters.CountersListAdapter;
import com.mashushka.mashushka.ui.listeners.CounterOpener;
import com.mashushka.mashushka.viewmodel.CountersListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements View.OnClickListener {

    private CounterOpener opener;
    private CreateCounterListener listener;

    public interface CreateCounterListener {
        void createCounter();
    }

    @BindView(android.R.id.list) RecyclerView list;
    @BindView(R.id.fab) FloatingActionButton fab;

    CountersListAdapter adapter = null;
    List<CounterEntity> data = new ArrayList<>();

    private CountersListViewModel mCountersListViewModel;

    public ListFragment() {
    }

    public static ListFragment newInstance(CreateCounterListener listener, CounterOpener opener) {

        ListFragment fragment = new ListFragment();
        fragment.opener = opener;
        fragment.listener = listener;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCountersListViewModel = ViewModelProviders.of(this).get(CountersListViewModel.class);
        mCountersListViewModel.initObservers();
        mCountersListViewModel.getCounters().observe(this, new Observer<List<CounterEntity>>() {
            @Override
            public void onChanged(@Nullable List<CounterEntity> entities) {
                data = entities;
                if(adapter == null) {
                    adapter = new CountersListAdapter(getActivity(), data, opener);
                    list.setAdapter(adapter);
                }

                adapter.setCountersList(data);
                adapter.notifyDataSetChanged();
            }
        });

        fab.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                listener.createCounter();
                break;
        }
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
