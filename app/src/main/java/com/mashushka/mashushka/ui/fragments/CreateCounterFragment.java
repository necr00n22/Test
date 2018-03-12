package com.mashushka.mashushka.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;
import com.mashushka.mashushka.ui.listeners.OnCounterCreatedListener;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateCounterFragment extends Fragment {

    @BindView(R.id.et_title) EditText title;
    @BindView(R.id.btn_confirm) Button confirm;
    private OnCounterCreatedListener listener;

    public CreateCounterFragment() {

    }

    public void setOnCreateCounterListener(OnCounterCreatedListener listener) {
        this.listener = listener;
    }

    public static CreateCounterFragment newInstance(OnCounterCreatedListener listener) {
        CreateCounterFragment fragment = new CreateCounterFragment();
        fragment.setOnCreateCounterListener(listener);
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_counter, container, false);
        ButterKnife.bind(this, v);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                CounterEntity counter = new CounterEntity(title.getText().toString(), date.getTime(), date.getTime(), 0);
                DataRepository.getInstance(getActivity()).insertSingleCounter(counter);
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
