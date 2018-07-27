package com.mashushka.mashushka.ui.holders;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.Utils;
import com.mashushka.mashushka.data.entity.CommentEntity;
import com.mashushka.mashushka.ui.CommentsProvider;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 26.07.2018.
 */

public class AddComentHolder extends BaseViewHolder<CommentEntity> {

    private final CommentsProvider provider;
    @BindView(R.id.et_text)
    EditText text;
    @BindView(R.id.add)
    Button add;
    private CommentEntity comment;

    public AddComentHolder(View itemView, CommentsProvider provider) {
        super(itemView);
        this.provider = provider;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment.setType(CommentEntity.TYPE_COMMENT);
                comment.setText(text.getText().toString());
                provider.addComment(comment);
            }
        });
    }

    @Override
    public void bind(CommentEntity comment) {
        this.comment = comment;
    }
}
