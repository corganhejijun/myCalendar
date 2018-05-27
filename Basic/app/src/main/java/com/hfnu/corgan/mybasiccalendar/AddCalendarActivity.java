package com.hfnu.corgan.mybasiccalendar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import java.util.Calendar;

public class AddCalendarActivity extends AppCompatActivity {
    Marker marker;
    String strBeginTime;
    String strEndTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_calendar);
        Intent intent = getIntent();
        final String strDate = intent.getStringExtra("date");
        AddCalendarActivity.this.setTitle(strDate);
        Button btn = findViewById(R.id.button_add_calendar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.editText_add_calendar_title);
                Bundle bun = new Bundle();
                bun.putString("date", strDate);
                bun.putString("title", title.getText().toString());
                bun.putString("beginTime", strBeginTime);
                bun.putString("endTime", strEndTime);
                if (marker != null)
                    bun.putString("position", marker.getPosition().toString());
                Intent intent = getIntent();
                intent.putExtras(bun);
                AddCalendarActivity.this.setResult(getResources().getInteger(R.integer.add_calendar_ok), intent);
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
                        strBeginTime = hourOfDay + ":" + minute;
                        beginTime.setText(strBeginTime);
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
                        strEndTime = hourOfDay + ":" + minute;
                        endTime.setText(strEndTime);
                    }
                }, hour, minute, true);
                dlg.show();
            }
        });
        MapView mapView = findViewById(R.id.mapView);
        final TencentMap tencentMap = mapView.getMap();
        // 设置默认经纬度为合肥
        tencentMap.setCenter(new LatLng(31.86, 117.27));
        tencentMap.setOnMapClickListener(new TencentMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng latLng) {
                if (marker != null)
                    marker.remove();
                marker = tencentMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }
}
