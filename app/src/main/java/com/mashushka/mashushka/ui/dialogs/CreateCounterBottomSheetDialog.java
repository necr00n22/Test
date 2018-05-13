package com.mashushka.mashushka.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mashushka.mashushka.R;
import com.mashushka.mashushka.data.entity.CounterEntity;
import com.mashushka.mashushka.database.DataRepository;

import java.util.Date;

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

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_create_counter, null);
        dialog.setContentView(contentView);

        ButterKnife.bind(this, contentView);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();

                if(!title.isEmpty())
                    createCounter(title);
            }
        });
    }

    private void createCounter(String title) {
        Date date = new Date();
        CounterEntity counter = new CounterEntity(title, date.getTime(), date.getTime(), 0, 0);
        DataRepository.getInstance(getActivity()).insertSingleCounter(counter);
        dismiss();
    }

}
