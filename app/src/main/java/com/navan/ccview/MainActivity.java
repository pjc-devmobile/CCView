package com.navan.ccview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.navan.customcalendarview.utils.DateUtils;
import com.navan.customcalendarview.view.CustomCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomCalendarView customCalendarView = findViewById(R.id.custom_calendar_view);

        List<Integer> daysWeek = new ArrayList<>();
        daysWeek.add(1);
        // daysWeek.add(2);
        // daysWeek.add(3);
        // daysWeek.add(4);
        // daysWeek.add(5);
        // daysWeek.add(6);
        daysWeek.add(7);
        customCalendarView.setDaysWeekDisbales(daysWeek);

        Calendar cmin = Calendar.getInstance();
        cmin.setTime(DateUtils.createDate("31/05/2019"));

        Calendar cmax = Calendar.getInstance();
        cmax.setTime(DateUtils.createDate("15/06/2019"));

        customCalendarView.setMinDate(cmin);
        customCalendarView.setMaxDate(cmax);

        List<Calendar> enables = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.createDate("28/06/2019"));
        enables.add(calendar);

        customCalendarView.setSpecialDatesEnables(enables);

        customCalendarView.setOnDaySelectedListener(new CustomCalendarView.OnDaySelectedListener() {
            @Override
            public void onDaySelected(Calendar date) {
                Toast.makeText(getApplicationContext(), DateUtils.formatDateExtenso(date.getTime()), Toast.LENGTH_LONG).show();
            }
        });

        // customCalendarView.setDateSelected(new Date());
        Calendar calendarSeleted = Calendar.getInstance();
        calendarSeleted.setTime(DateUtils.createDate("20/06/2019"));
        customCalendarView.setDateSelected(calendar);
    }
}
