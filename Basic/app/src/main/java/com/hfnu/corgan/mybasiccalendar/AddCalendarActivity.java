package com.hfnu.corgan.mybasiccalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                EditText beginTime = findViewById(R.id.editText_add_calendar_begin_time);
                EditText endTime = findViewById(R.id.editText_add_calendar_end_time);
                Bundle bun = new Bundle();
                bun.putString("title", title.getText().toString());
                bun.putString("begin", beginTime.getText().toString());
                bun.putString("end", endTime.getText().toString());
                Intent intent = getIntent();
                intent.putExtras(bun);
                AddCalendarActivity.this.setResult(0, intent);
                AddCalendarActivity.this.finish();
            }
        });
    }
}
