package com.mashushka.mashushka.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CommentEntity;
import com.mashushka.mashushka.ui.CommentsProvider;
import com.mashushka.mashushka.ui.holders.AddComentHolder;
import com.mashushka.mashushka.ui.holders.CommentHolder;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import java.util.List;

import static com.mashushka.mashushka.data.entity.CommentEntity.TYPE_ADD_COMMENT;
import static com.mashushka.mashushka.data.entity.CommentEntity.TYPE_COMMENT;

/**
 * Created by Mikhail Li (Jiub) on 27.07.2018.
 */

public class CommentsAdapter extends RecyclerView.Adapter<BaseViewHolder<CommentEntity>> {

    private final Context mContext;
    private List<CommentEntity> mItems;
    private CommentsProvider commentsProvider;

    public CommentsAdapter(Context context, List<CommentEntity> items, CommentsProvider commentsProvider) {
        this.mContext = context;
        this.mItems = items;
        this.commentsProvider = commentsProvider;
    }

    @NonNull
    @Override
    public BaseViewHolder<CommentEntity> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_COMMENT:
                return new CommentHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_comment, parent, false));
            case TYPE_ADD_COMMENT:
                return new AddComentHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_add_comment, parent, false), commentsProvider);
            default:
                return new CommentHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_comment, parent, false));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<CommentEntity> holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<CommentEntity> comments) {
        mItems = comments;
        notifyDataSetChanged();
    }
}
