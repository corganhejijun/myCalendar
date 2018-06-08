package com.hfnu.corgan.mycalendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarGridAdapter extends BaseAdapter {
    private Context context;
    private Calendar date;
    private int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public  CalendarGridAdapter(Context context, Calendar date){
        this.context = context;
        this.date = date;
        checkForLeapYear(date);
    }

    @Override
    public int getCount() {
        Calendar firstDateOfMonth = firstDayOfMonth(date);
        int dateCnt = daysOfMonth[date.get(Calendar.MONTH)] + getWeekDayOfFirstDateOfMonth(firstDateOfMonth);
        // 显示完整一周的日历
        if (dateCnt % 7 != 0) {
            dateCnt += 7 - dateCnt % 7;
        }
        return dateCnt;
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
        Calendar posDate = getDate(position);
        Calendar current = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        if (format1.format(posDate.getTime()).equals(format1.format(current.getTime())))
            text.setBackgroundColor(Color.GRAY);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
        if (!format2.format(posDate.getTime()).equals(format2.format(date.getTime())))
            text.setTextColor(0x33333333);
        text.setText(posDate.get(Calendar.DAY_OF_MONTH) + "");
        return view;
    }

    public String getDateString(int position){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(getDate(position).getTime());
        return formatted;
    }

    public String getMonthString(){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        String formatted = format1.format(date.getTime());
        return formatted;
    }

    public void setPrevMonth(){
        int yearBefore = date.get(Calendar.YEAR);
        date.add(Calendar.MONTH, -1);
        int yearAfter = date.get(Calendar.YEAR);
        if (yearAfter != yearBefore)
            checkForLeapYear(date);
    }

    public void setNextMonth(){
        int yearBefore = date.get(Calendar.YEAR);
        date.add(Calendar.MONTH, 1);
        int yearAfter = date.get(Calendar.YEAR);
        if (yearAfter != yearBefore)
            checkForLeapYear(date);
    }

    public void setCurrentMonth(){
        int yearBefore = date.get(Calendar.YEAR);
        date = Calendar.getInstance();
        int yearAfter = date.get(Calendar.YEAR);
        if (yearAfter != yearBefore)
            checkForLeapYear(date);
    }

    private Calendar getDate(int position){
        // 日历按星期排序，第一天为星期天
        Calendar firstDateOfMonth = firstDayOfMonth(date);
        firstDateOfMonth.add(Calendar.DATE, position - getWeekDayOfFirstDateOfMonth(firstDateOfMonth));
        return firstDateOfMonth;
    }

    private Calendar firstDayOfMonth(Calendar date){
        Calendar myDate = (Calendar) date.clone();
        myDate.set(Calendar.DAY_OF_MONTH, 1);
        return myDate;
    }

    private int getWeekDayOfFirstDateOfMonth(Calendar date){
        // 星期的编号从1开始，1代表星期日
        int firstDateInWeek = date.get(Calendar.DAY_OF_WEEK);
        return firstDateInWeek - 1;
    }

    private void checkForLeapYear(Calendar date){
        int year = date.get(Calendar.YEAR);
        if (isLeapYear(year))
            daysOfMonth[1] = 29;
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
