package com.navan.ccview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.navan.customcalendarview.utils.DateUtils;
import com.navan.customcalendarview.view.CustomCalendarView;

import java.util.ArrayList;
import java.util.Date;
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

        customCalendarView.setMinDate(DateUtils.createDate("31/05/2019"));
        customCalendarView.setMaxDate(DateUtils.createDate("15/06/2019"));

        List<Date> enables = new ArrayList<>();
        enables.add(DateUtils.createDate("28/06/2019"));

        customCalendarView.setSpecialDatesEnables(enables);

        customCalendarView.setOnDaySelectedListener(new CustomCalendarView.OnDaySelectedListener() {
            @Override
            public void onDaySelected(Date date) {
                Toast.makeText(getApplicationContext(), DateUtils.formatDateExtenso(date), Toast.LENGTH_LONG).show();
            }
        });

        customCalendarView.setDateSelected(new Date());
    }
}
