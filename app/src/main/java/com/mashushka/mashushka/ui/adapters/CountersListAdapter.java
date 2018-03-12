package com.mashushka.mashushka.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Counter;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.ui.holders.CounterHolder;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Mikhail Li (Jiub) on 03.02.2018.
 */

public class CountersListAdapter extends RecyclerView.Adapter<CounterHolder> {

    private Context mContext;
    private List<CounterEntity> counters;

    public CountersListAdapter(Context context, List<CounterEntity> counters) {

        mContext = context;
        this.counters = counters;
    }

    @Override
    public CounterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CounterHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_counter, parent, false));
    }

    public void setCountersList(List<CounterEntity> data) {
        this.counters = data;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CounterHolder holder, int position) {
        holder.bind(counters.get(position));
    }

    @Override
    public int getItemCount() {
        return counters.size();
    }
}
