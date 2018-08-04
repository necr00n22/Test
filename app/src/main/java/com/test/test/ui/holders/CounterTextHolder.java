package com.test.test.ui.holders;

import android.view.View;
import android.widget.TextView;

import com.test.test.R;
import com.test.test.data.Block;
import com.test.test.ui.holders.base.BaseViewHolder;

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
