package com.bank.axisbank.HELPER;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.DatePicker;
import androidx.appcompat.widget.AppCompatEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateEditText extends AppCompatEditText {

    private Context context;

    public DateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int drawableRight = getRight() - getCompoundDrawables()[2].getBounds().width();
            if (event.getRawX() >= drawableRight) {
                showDatePickerDialog();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,android.R.style.Theme_Holo_Light_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // When a date is selected, update the EditText with the selected date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                calendar.set(year, monthOfYear, dayOfMonth);
                String selectedDate = sdf.format(calendar.getTime());
                setText(selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}

