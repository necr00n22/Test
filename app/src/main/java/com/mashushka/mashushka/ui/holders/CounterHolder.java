package com.mashushka.mashushka.ui.holders;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.ui.CircleProgressBar;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;
import com.mashushka.mashushka.ui.listeners.CounterOpener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 03.02.2018.
 */

public class CounterHolder extends BaseViewHolder<CounterEntity>{

    private final CounterOpener listener;
    @BindView(R.id.wrapper) RelativeLayout wrapper;
    @BindView(R.id.counter) TextView counter;
    @BindView(R.id.create_date) TextView createDate;
    @BindView(R.id.tv_days_title) TextView daysTitle;
    @BindView(R.id.custom_progressBar) CircleProgressBar progress;

    public CounterHolder (View itemView, CounterOpener listener) {
        super(itemView);
        this.listener = listener;
    }

    @Override
    public void bind(CounterEntity data) {
        if(title != null)
        title.setText(data.getTitle());
        int allDays;

        allDays = (int) ((data.getEndDate() -  data.getCreateDate()) / (1000 * 60 * 60 *24));

        Date createTime = new Date();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        createTime.setTime(data.getCreateDate());

        Date timePassed = new Date();
        timePassed.setTime(now.getTime() - createTime.getTime());

        int daysPassed = (int) timePassed.getTime() / (1000 * 60 * 60 *24);

        long time = timePassed.getTime() / 1000;

        long days;

        progress.setMax(allDays);
        progress.setProgress(daysPassed);

        days = time / (3600 * 24);

        createDate.setText(format.format(createTime));
        counter.setText(String.valueOf(days));

        daysTitle.setText(daysTitle.getContext().getResources().getQuantityText(R.plurals.days, (int) days));
        wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showCounter(data.id);
            }
        });
    }
}


