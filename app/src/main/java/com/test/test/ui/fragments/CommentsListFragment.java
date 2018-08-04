package com.test.test.ui.fragments;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.test.R;
import com.test.test.data.entity.CommentEntity;
import com.test.test.ui.CommentsProvider;
import com.test.test.ui.adapters.CommentsAdapter;
import com.test.test.viewmodel.CommentsListViewModel;
import com.test.test.viewmodel.factory.CommentsListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.test.test.Constants.Extras.EXTRA_COUNTER_ID;

/**
 * Created by Михаил on 03.08.2018.
 */

public class CommentsListFragment extends Fragment implements CommentsProvider {

    @BindView(android.R.id.list)
    RecyclerView list;

    CommentsAdapter adapter;
    private long id;
    private CommentsListViewModel mCommentsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        id = bundle.getLong(EXTRA_COUNTER_ID, -1L);
        CommentsListViewModelFactory factory = new CommentsListViewModelFactory(getActivity().getApplication(), id);
        mCommentsViewModel = ViewModelProviders.of(this, factory).get(CommentsListViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new CommentsAdapter(getContext(), new ArrayList<CommentEntity>(), this);
        list.setLayoutManager(llm);
        list.setAdapter(adapter);

        mCommentsViewModel.getComments().observe(this, new Observer<List<CommentEntity>>() {
            @Override
            public void onChanged(@Nullable List<CommentEntity> commentEntities) {
                adapter.setItems(commentEntities);
            }
        });
    }

    public static Fragment newInstance(long counterId) {
        CommentsListFragment fragment = new CommentsListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_COUNTER_ID, counterId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public LiveData<List<CommentEntity>> getComments() {
        return null;
    }

    @Override
    public void addComment(CommentEntity comment) {

    }

    @Override
    public void showComment(long commentId) {

    }

    @Override
    public void showAllComments() {

    }

    @Override
    public LifecycleOwner getLyfecycleOwner() {
        return null;
    }
}
