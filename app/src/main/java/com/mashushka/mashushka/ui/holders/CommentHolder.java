package com.mashushka.mashushka.ui.holders;

import android.view.View;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.Utils;
import com.mashushka.mashushka.data.Block;
import com.mashushka.mashushka.data.entity.CommentEntity;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import java.util.Date;

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
