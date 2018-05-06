package com.hfnu.corgan.mybasiccalendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarGridAdapter extends BaseAdapter {
    private Context context;
    private Calendar date;
    private int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public  CalendarGridAdapter(Context context, Calendar date){
        this.context = context;
        this.date = date;
        int year = date.get(Calendar.YEAR);
        if (isLeapYear(year))
            daysOfMonth[1] = 29;
    }

    @Override
    public int getCount() {
        Calendar firstDateOfMonth = firstDayOfMonth(Calendar.getInstance());
        return daysOfMonth[date.get(Calendar.MONTH)] + getWeekDayOfFirstDateOfMonth(firstDateOfMonth);
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.date_item, parent, false);
        TextView text = view.findViewById(R.id.date_item_text);
        Calendar date = getDate(position);
        if (date.equals(Calendar.getInstance()))
            text.setBackgroundColor(Color.GRAY);
        text.setText(getDate(position).get(Calendar.DAY_OF_MONTH) + "");
        return view;
    }

    public String getDateString(int position){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(getDate(position).getTime());
        return formatted;
    }

    private Calendar getDate(int position){
        // 日历按星期排序，第一天为星期天
        Calendar firstDateOfMonth = firstDayOfMonth(Calendar.getInstance());
        firstDateOfMonth.add(Calendar.DATE, position - getWeekDayOfFirstDateOfMonth(firstDateOfMonth));
        return firstDateOfMonth;
    }

    private Calendar firstDayOfMonth(Calendar date){
        date.set(Calendar.DAY_OF_MONTH, 1);
        return date;
    }

    private int getWeekDayOfFirstDateOfMonth(Calendar date){
        // 星期的编号从1开始，1代表星期日
        int firstDateInWeek = date.get(Calendar.DAY_OF_WEEK);
        return firstDateInWeek - 1;
    }

    private boolean isLeapYear(int year){
        if (year % 4 == 0){
            if (year % 100 == 0)
                if (year % 400 == 0)
                    return true;
                else
                    return false;
            return true;
        }
        return false;
    }
}
