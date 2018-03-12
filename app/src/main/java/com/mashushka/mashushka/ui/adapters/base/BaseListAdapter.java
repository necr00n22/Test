package com.mashushka.mashushka.ui.adapters.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Mikhail Li (Jiub) on 03.02.2018.
 */

public abstract class BaseListAdapter<H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(H holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
