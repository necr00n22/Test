package com.test.test.ui.holders;

import android.view.View;
import android.widget.TextView;

import com.test.test.R;
import com.test.test.Utils;
import com.test.test.data.Block;
import com.test.test.ui.CircleProgressBar;
import com.test.test.ui.holders.base.BaseViewHolder;

import java.util.Date;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 26.07.2018.
 */

public class CounterProgressHolder extends BaseViewHolder<Block> {

    @BindView(R.id.progress)
    CircleProgressBar progress;
    @BindView(R.id.days_past)
    TextView daysPast;
    @BindView(R.id.days_left)
    TextView daysLeft;

    public CounterProgressHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Block data) {
        if(data.getStartDate() != -1L && data.getEndDate() != -1L) {
            Date timePassed = new Date(new Date().getTime() - data.getStartDate());
            int daysPassed = (int) timePassed.getTime() / (1000 * 60 * 60 * 24);
            daysPast.setText(Utils.DateUtils.daysPassedString(itemView.getContext(), data.getStartDate(), new Date().getTime()));
            daysLeft.setText(Utils.DateUtils.daysLeftString(itemView.getContext(),data.getEndDate(), new Date().getTime()));
            progress.setProgress(daysPassed);
        }
    }
}
