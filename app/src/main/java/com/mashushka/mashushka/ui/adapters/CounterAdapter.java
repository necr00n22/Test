package com.mashushka.mashushka.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Block;
import com.mashushka.mashushka.ui.CommentsProvider;
import com.mashushka.mashushka.ui.holders.CounterCommentsHolder;
import com.mashushka.mashushka.ui.holders.CounterProgressHolder;
import com.mashushka.mashushka.ui.holders.CounterTextHolder;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail Li (Jiub) on 26.07.2018.
 */

public class CounterAdapter extends RecyclerView.Adapter<BaseViewHolder<Block>> {

    public final static int TYPE_TEXT = 0;
    public final static int TYPE_COUNTER = 1;
    public final static int TYPE_NUMBER = 2;
    public final static int TYPE_COMMENTS = 3;

    private Context context;
    private List<Block> mItems;
    private CommentsProvider commentsProvider;

    public CounterAdapter(Context context, ArrayList<Block> mItems, CommentsProvider commentsProvider) {
        this.context = context;
        this.mItems = mItems;
        this.commentsProvider = commentsProvider;
    }

    @NonNull
    @Override
    public BaseViewHolder<Block> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_COMMENTS:
                return new CounterCommentsHolder(LayoutInflater.from(context).inflate(R.layout.counter_item_comments_list, parent, false), commentsProvider);
            case TYPE_COUNTER:
                return new CounterProgressHolder(LayoutInflater.from(context).inflate(R.layout.counter_item_progress, parent, false));
            case TYPE_TEXT:
                return new CounterTextHolder(LayoutInflater.from(context).inflate(R.layout.counter_item_text, parent, false));
            case TYPE_NUMBER:
                return new CounterProgressHolder(LayoutInflater.from(context).inflate(R.layout.counter_item_progress, parent, false));
            default:
                return new CounterProgressHolder(LayoutInflater.from(context).inflate(R.layout.counter_item_progress, parent, false));
        }
    }



    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<Block> holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (mItems.get(position).getType()) {
            case Comments:
                return TYPE_COMMENTS;
            case Counter:
                return TYPE_COUNTER;
            case SimpleText:
                return TYPE_TEXT;
            case Number:
                return TYPE_NUMBER;
            default:
                return TYPE_TEXT;

        }
    }

    public void setItems(List<Block> blocks) {
        mItems = blocks;
        notifyDataSetChanged();
    }
}
