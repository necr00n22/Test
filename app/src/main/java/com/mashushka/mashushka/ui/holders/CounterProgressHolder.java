package com.mashushka.mashushka.ui.holders;

import android.view.View;
import android.widget.TextView;

import com.mashushka.mashushka.R;
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
            Date timePassed = new Date();
            timePassed.setTime(new Date().getTime() - data.getStartDate());
            Date timeLeft = new Date();
            timeLeft = new Date (data.getEndDate() - timeLeft.getTime());
            long dPassed = timePassed.getTime() / (3600 * 24 * 1000) + 1;
            long dLeft = timeLeft.getTime() / (3600 * 24 * 1000);
            int daysPassed = (int) timePassed.getTime() / (1000 * 60 * 60 *24);
            daysPast.setText(String.format(Locale.getDefault() ,"%s %d", itemView.getResources().getString(R.string.day), dPassed));
            daysLeft.setText(String.format(Locale.getDefault() ,"%s %d %s", itemView.getResources().getString(R.string.days_left), dLeft, itemView.getResources().getQuantityString(R.plurals.days, (int) dLeft)));
            progress.setProgress(daysPassed);
        }
    }
}
