package com.mashushka.mashushka.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Counter;
import com.mashushka.mashushka.database.DB;
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
                DB db = new DB(getActivity());
                Date date = new Date();
                Counter counter = new Counter(title.getText().toString(), date.getTime(), date.getTime(), 0);
                db.insertData(counter.createContentValues(), DB.TABLE_COUNTERS);
                if (listener != null) listener.onCounterCreated(counter);
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
