package com.hfnu.corgan.mybasiccalendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定布局文件
        setContentView(R.layout.first_layout);

        // 初始化用户类
        SharedPreferences share = getSharedPreferences(getResources().getString(R.string.config_file_path), MODE_PRIVATE);
        users = new Users(share);

        // 日期GridView
        final CalendarGridAdapter gridAdapter = new CalendarGridAdapter(this, Calendar.getInstance());
        final GridView gridView = findViewById(R.id.grid_view);
        MainActivity.this.setTitle(gridAdapter.getMonthString());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddCalendarActivity.class);
                intent.putExtra("date", gridAdapter.getDateString(position));
                startActivityForResult(intent, 0);
            }
        });

        // 按钮功能
        Button btnPrev = findViewById(R.id.buttonPrevMonth);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdapter.setPrevMonth();
                gridView.invalidateViews();
                MainActivity.this.setTitle(gridAdapter.getMonthString());
            }
        });
        Button btnNext = findViewById(R.id.buttonNextMonth);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdapter.setNextMonth();
                gridView.invalidateViews();
                MainActivity.this.setTitle(gridAdapter.getMonthString());
            }
        });
        Button btnToday = findViewById(R.id.buttonToday);
        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdapter.setCurrentMonth();
                gridView.invalidateViews();
                MainActivity.this.setTitle(gridAdapter.getMonthString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getResources().getInteger(R.integer.login_ok)){
            Toast.makeText(this, R.string.message_login_success, Toast.LENGTH_SHORT).show();
            users.login(data.getStringExtra("username"), data.getStringExtra("password"));

        }
        else if (resultCode == getResources().getInteger(R.integer.register_ok)){
            Toast.makeText(this, R.string.message_register_success, Toast.LENGTH_SHORT).show();
            users.register(data.getStringExtra("username"), data.getStringExtra("password"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SharedPreferences share = getSharedPreferences(getResources().getString(R.string.config_file_path), MODE_PRIVATE);
        String password = share.getString("password", "");
        String username = share.getString("username", "");
        if (!users.checkLogin(username, password)){
            Toast.makeText(this, username + " " + getResources().getString(R.string.message_login_fail), Toast.LENGTH_SHORT).show();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_item:
                if (item.getTitle().equals(getResources().getString(R.string.menu_user))) {
                    Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                    startActivityForResult(intent, getResources().getInteger(R.integer.login_ok));
                }
                else{
                    users.logout();
                    item.setTitle(R.string.menu_user);
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String username = users.username;
        MenuItem item = menu.findItem(R.id.user_item);
        if (username.equals("")){
            item.setTitle(getResources().getString(R.string.menu_user));
        }
        else{
            item.setTitle(getResources().getString(R.string.menu_logout) + " " + username);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
