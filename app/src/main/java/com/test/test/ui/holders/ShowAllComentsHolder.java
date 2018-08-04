package com.test.test.ui.holders;

import android.view.View;
import android.widget.Button;

import com.test.test.R;
import com.test.test.data.entity.CommentEntity;
import com.test.test.ui.CommentsProvider;
import com.test.test.ui.holders.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 26.07.2018.
 */

public class ShowAllComentsHolder extends BaseViewHolder<CommentEntity> {

    private final CommentsProvider provider;
    @BindView(R.id.show_all_comments)
    Button showAllComments;
    private CommentEntity comment;

    public ShowAllComentsHolder(View itemView, CommentsProvider provider) {
        super(itemView);
        this.provider = provider;

        showAllComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provider.showAllComments();
            }
        });
    }

    @Override
    public void bind(CommentEntity comment) {
        this.comment = comment;
    }
}
