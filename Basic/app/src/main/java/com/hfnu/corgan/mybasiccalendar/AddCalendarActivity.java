package com.hfnu.corgan.mybasiccalendar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_calendar);
        Intent intent = getIntent();
        AddCalendarActivity.this.setTitle(intent.getStringExtra("date"));
        Button btn = findViewById(R.id.button_add_calendar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.editText_add_calendar_title);
                Bundle bun = new Bundle();
                bun.putString("title", title.getText().toString());
                Intent intent = getIntent();
                intent.putExtras(bun);
                AddCalendarActivity.this.setResult(0, intent);
                AddCalendarActivity.this.finish();
            }
        });
        final Button beginTime = findViewById(R.id.editText_add_calendar_begin_time);
        Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        beginTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dlg = new TimePickerDialog(AddCalendarActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        beginTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                dlg.show();
            }
        });
        final Button endTime = findViewById(R.id.editText_add_calendar_end_time);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dlg = new TimePickerDialog(AddCalendarActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                dlg.show();
            }
        });
    }
}
