package com.test.test.ui.holders;

import android.view.View;
import android.widget.TextView;

import com.test.test.R;
import com.test.test.Utils;
import com.test.test.data.entity.CommentEntity;
import com.test.test.ui.holders.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 26.07.2018.
 */

public class CommentHolder extends BaseViewHolder<CommentEntity> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text)
    TextView text;

    public CommentHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(CommentEntity comment) {
        this.title.setText(Utils.DateUtils.daysPassedString(itemView.getContext(), comment.getCounterCreatedDate().getTime(), comment.getCreatedDate().getTime()));
        text.setText(comment.getText() != null ? comment.getText() : "");
    }
}
