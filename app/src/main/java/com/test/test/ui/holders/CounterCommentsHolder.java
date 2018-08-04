package com.test.test.ui.holders;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.test.test.R;
import com.test.test.data.Block;
import com.test.test.data.entity.CommentEntity;
import com.test.test.ui.CommentsProvider;
import com.test.test.ui.adapters.CommentsAdapter;
import com.test.test.ui.holders.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 26.07.2018.
 */

public class CounterCommentsHolder extends BaseViewHolder<Block> {

    private LiveData<List<CommentEntity>> commentsLiveData;
    private final LinearLayoutManager linearLayoutManager;
    private final CommentsProvider commentsProvider;
    private CommentsAdapter adapter;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.list)
    RecyclerView list;

    public CounterCommentsHolder(View itemView, CommentsProvider commentsProvider) {
        super(itemView);
        this.commentsProvider = commentsProvider;
        linearLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(linearLayoutManager);
        adapter = new CommentsAdapter(itemView.getContext(), new ArrayList<>(), commentsProvider);
        list.setAdapter(adapter);
        commentsLiveData = commentsProvider.getComments();
    }

    @Override
    public void bind(Block data) {
        title.setText(data.getTitle());
        if(commentsLiveData.hasActiveObservers())
            commentsLiveData.removeObservers(commentsProvider.getLyfecycleOwner());

        commentsLiveData.observe(commentsProvider.getLyfecycleOwner(), new Observer<List<CommentEntity>>() {
            @Override
            public void onChanged(@Nullable List<CommentEntity> commentEntities) {
                if(commentEntities != null) {
                    commentEntities.add(0, createAddCommentItem());
                    if(commentEntities.size() == 4)
                        commentEntities.add(createShowCommentsItem());
                    adapter.setItems(commentEntities);
                }
            }
        });
    }

    public CommentEntity createAddCommentItem() {
        CommentEntity addComment = new CommentEntity(-1, new Date(), new Date(), "");
        addComment.setType(CommentEntity.TYPE_ADD_COMMENT);
        return addComment;
    }

    public CommentEntity createShowCommentsItem() {
        CommentEntity showComments = new CommentEntity(-1, new Date(), new Date(), "");
        showComments.setType(CommentEntity.TYPE_SHOW_ALL_COMMENTS);
        return showComments;
    }
}
