package com.mashushka.mashushka.ui.holders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.Counter;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.ui.holders.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by Mikhail Li (Jiub) on 03.02.2018.
 */

public class CounterHolder extends BaseViewHolder<CounterEntity> {

    @BindView(R.id.icon) ImageView icon;
    @BindView(R.id.counter) TextView counter;
    @BindView(R.id.create_date) TextView createDate;

    public CounterHolder (View itemView) {
        super(itemView);
    }

    @Override
    public void bind(CounterEntity data) {
        if(title != null)
        title.setText(data.getTitle());

        Date createTime = new Date();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        createTime.setTime(data.getCreateDate());

        Date timePassed = new Date();
        timePassed.setTime(now.getTime() - createTime.getTime());

        long time = timePassed.getTime() / 1000;

        long seconds;
        long minutes;
        long hours;
        long days;

        days = time / (3600 * 24);
        hours = (time - (days * (3600 * 24))) / (3600);
        minutes = (time - (days * (3600 * 24)) - (hours * 3600)) / (60);
        seconds =  (time - days * (3600 * 24) -  hours * 3600 - minutes  * 60);

        createDate.setText(format.format(createTime));
        counter.setText(days + " " + hours + " " + minutes + " " + seconds);
    }
}


