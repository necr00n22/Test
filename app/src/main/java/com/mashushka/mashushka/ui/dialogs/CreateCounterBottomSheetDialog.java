package com.mashushka.mashushka.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mikhail Li (Jiub) on 12.05.2018.
 */

public class CreateCounterBottomSheetDialog extends BottomSheetDialogFragment {

    public final static String TAG = "CreateCounterBottomSheetDialog";

    @BindView(R.id.btn_create)
    Button btnCreate;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.sb_days)
    SeekBar sbDays;
    @BindView(R.id.tv_days)
    TextView tvDays;

    int days;
    String title;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_create_counter, null);
        dialog.setContentView(contentView);

        ButterKnife.bind(this, contentView);


        days = 27;
        tvDays.setText(String.valueOf(days));
        sbDays.setProgress(days);

        sbDays.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvDays.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                days = seekBar.getProgress();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = etTitle.getText().toString();
                if(!title.isEmpty())
                    createCounter();
            }
        });
    }

    private void createCounter() {
        GregorianCalendar createDate = new GregorianCalendar();
        createDate.setTime(new Date());
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.setTime(new Date());
        endDate.add(Calendar.HOUR, days * 24);

        CounterEntity counter = new CounterEntity(title, createDate.getTimeInMillis(), endDate.getTimeInMillis());
        DataRepository.getInstance(getActivity()).insertSingleCounter(counter);
        dismiss();
    }

}
