package com.test.test.ui.holders.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Mikhail Li (Jiub) on 03.02.2018.
 */

public abstract class BaseViewHolder<H> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(H data);
}
