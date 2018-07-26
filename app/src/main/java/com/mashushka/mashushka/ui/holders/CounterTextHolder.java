package com.mashushka.mashushka.ui.holders;

import android.view.View;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Block;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 26.07.2018.
 */

public class CounterTextHolder extends BaseViewHolder<Block> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;

    public CounterTextHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Block data) {
        title.setText(data.getTitle());
        description.setText(data.getDescription() != null ? data.getDescription() : "");
    }
}
