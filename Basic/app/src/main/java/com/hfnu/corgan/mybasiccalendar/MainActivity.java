package com.hfnu.corgan.mybasiccalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        final CalendarGridAdapter gridAdpter = new CalendarGridAdapter(this, Calendar.getInstance());
        final GridView gridView = findViewById(R.id.grid_view);
        MainActivity.this.setTitle(gridAdpter.getMonthString());
        gridView.setAdapter(gridAdpter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddCalendarActivity.class);
                intent.putExtra("date", gridAdpter.getDateString(position));
                startActivityForResult(intent, 0);
            }
        });
        Button btnPrev = findViewById(R.id.buttonPrevMonth);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdpter.setPrevMonth();
                gridView.invalidateViews();
                MainActivity.this.setTitle(gridAdpter.getMonthString());
            }
        });
        Button btnNext = findViewById(R.id.buttonNextMonth);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdpter.setNextMonth();
                gridView.invalidateViews();
                MainActivity.this.setTitle(gridAdpter.getMonthString());
            }
        });
        Button btnToday = findViewById(R.id.buttonToday);
        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdpter.setCurrentMonth();
                gridView.invalidateViews();
                MainActivity.this.setTitle(gridAdpter.getMonthString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == 0 && resultCode == 0){

         }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, R.string.menu_after_add, Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, R.string.menu_after_remove, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
