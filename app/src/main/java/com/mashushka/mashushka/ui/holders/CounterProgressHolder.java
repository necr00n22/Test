package com.mashushka.mashushka.ui.holders;

import android.view.View;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.Utils;
import com.mashushka.mashushka.data.Block;
import com.mashushka.mashushka.ui.CircleProgressBar;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import java.util.Date;
import java.util.Locale;

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
