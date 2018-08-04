package com.test.test.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test.test.R;
import com.test.test.data.Block;
import com.test.test.data.Type;
import com.test.test.data.entity.CounterEntity;
import com.test.test.database.CounterRepository;
import com.test.test.di.App;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mikhail Li (Jiub) on 12.05.2018.
 */

public class CreateCounterBottomSheetDialog extends BottomSheetDialogFragment {

    @Inject
    CounterRepository repository;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        return super.onCreateDialog(savedInstanceState);
    }

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

        List<Block> blocks = new ArrayList<>();

        Block.Builder counterBlock = new Block.Builder("", Type.Counter)
                .setStartDate(createDate.getTimeInMillis())
                .setEndDate(endDate.getTimeInMillis());
        blocks.add(counterBlock.build());

        Block.Builder descriptionBlock = new Block.Builder(getString(R.string.block_label_description), Type.SimpleText)
                .setDescription(title);
        blocks.add(descriptionBlock.build());

        Block.Builder commentsBlock = new Block.Builder(getString(R.string.block_label_comments), Type.Comments)
                .setStartDate(createDate.getTimeInMillis());
        blocks.add(commentsBlock.build());


        CounterEntity counter = new CounterEntity(title, createDate.getTimeInMillis(), endDate.getTimeInMillis(), blocks);
        repository.insertSingleCounter(counter);
        dismiss();
    }

}
