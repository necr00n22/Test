package com.test.test.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
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

import com.test.test.R;
import com.test.test.data.entity.CounterEntity;
import com.test.test.ui.adapters.CountersListAdapter;
import com.test.test.ui.dialogs.CreateCounterBottomSheetDialog;
import com.test.test.ui.listeners.CounterOpener;
import com.test.test.viewmodel.CountersListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    private CounterOpener opener;

    @BindView(android.R.id.list) RecyclerView list;

    CountersListAdapter adapter = null;
    List<CounterEntity> data = new ArrayList<>();

    private CountersListViewModel mCountersListViewModel;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        if(context instanceof CounterOpener)
            opener = (CounterOpener) context;
        super.onAttach(context);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_counter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                showCreateCounterDialog();
                return true;
        }

        return false;
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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

    private void showCreateCounterDialog() {
        CreateCounterBottomSheetDialog dialog = new CreateCounterBottomSheetDialog();
        dialog.show(getChildFragmentManager(), CreateCounterBottomSheetDialog.TAG);
    }

}
