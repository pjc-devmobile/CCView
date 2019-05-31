package com.navan.customcalendarview.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.navan.customcalendarview.R;

import java.util.Calendar;
import java.util.Date;

public class CustomCalendarView extends LinearLayout {

    private ViewHolder vh;
    private Date currentDate;
    private Date minDate, maxDate;
    private Calendar currentCalendar;
    private int lastDayMonthInsertInText;

    public CustomCalendarView(Context context) {
        super(context);
        init();
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(inflate(getContext(), R.layout.custom_calendar_view, null), layoutParams);
        vh = new ViewHolder();
        setCurrentDate(new Date());
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
        showCurrentDate();
    }

    private void showCurrentDate() {
        currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        //primeira semana
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.set(Calendar.DAY_OF_MONTH, 1);
        vh.line1.putFirstWeek(c.get(Calendar.DAY_OF_WEEK));

        vh.line2.putSecondLine();
        vh.line3.putThirdLine();
        vh.line4.putFourthLine();
        vh.line5.putFifthLine();
        vh.line6.putSexthdLine();
    }

    public class ViewHolder {

        private final ImageButton btnLeft, btnRight;
        private final TextView txtCurrentDate, txtDateSelected;
        private final LineNumbersDays line1, line2, line3, line4, line5, line6;

        public ViewHolder() {
            btnLeft = findViewById(R.id.btn_left);
            btnRight = findViewById(R.id.btn_right);

            txtCurrentDate = findViewById(R.id.txt_current_date);
            txtDateSelected = findViewById(R.id.txt_selected_date);

            line1 = new LineNumbersDays(findViewById(R.id.lay_number_days_1));
            line2 = new LineNumbersDays(findViewById(R.id.lay_number_days_2));
            line3 = new LineNumbersDays(findViewById(R.id.lay_number_days_3));
            line4 = new LineNumbersDays(findViewById(R.id.lay_number_days_4));
            line5 = new LineNumbersDays(findViewById(R.id.lay_number_days_5));
            line6 = new LineNumbersDays(findViewById(R.id.lay_number_days_6));
        }

    }

    private class LineNumbersDays {
        private int lastTextViewInsertId;
        private boolean isFull = false;

        private TextView txtSeg, txtTer, txtQua, txtQui, txtSex, txtSab, txtDom;
        private View viewSeg, viewTer, viewQua, viewQui, viewSex, viewSab, viewDom;

        public LineNumbersDays(View view) {
            txtSeg = view.findViewById(R.id.txt_seg);
            txtTer = view.findViewById(R.id.txt_ter);
            txtQua = view.findViewById(R.id.txt_qua);
            txtQui = view.findViewById(R.id.txt_qui);
            txtSex = view.findViewById(R.id.txt_sex);
            txtSab = view.findViewById(R.id.txt_sab);
            txtDom = view.findViewById(R.id.txt_dom);

            viewSeg = view.findViewById(R.id.view_seg);
            viewTer = view.findViewById(R.id.view_ter);
            viewQua = view.findViewById(R.id.view_qua);
            viewQui = view.findViewById(R.id.view_qui);
            viewSex = view.findViewById(R.id.view_sex);
            viewSab = view.findViewById(R.id.view_sab);
            viewDom = view.findViewById(R.id.view_dom);
        }

        public void putFirstWeek(int firstDayWeek) {
            if (firstDayWeek == Calendar.SUNDAY) {
                txtSeg.setText("");
                txtTer.setText("");
                txtQua.setText("");
                txtQui.setText("");
                txtSex.setText("");
                txtSab.setText("");
                txtDom.setText("1");
                lastDayMonthInsertInText = 1;
            } else if (firstDayWeek == Calendar.MONDAY) {
                txtSeg.setText("1");
                txtTer.setText("2");
                txtQua.setText("3");
                txtQui.setText("4");
                txtSex.setText("5");
                txtSab.setText("6");
                txtDom.setText("7");
                lastDayMonthInsertInText = 7;
            } else if (firstDayWeek == Calendar.TUESDAY) {
                txtSeg.setText(" ");
                txtTer.setText("1");
                txtQua.setText("2");
                txtQui.setText("3");
                txtSex.setText("4");
                txtSab.setText("5");
                txtDom.setText("6");
                lastDayMonthInsertInText = 6;
            } else if (firstDayWeek == Calendar.WEDNESDAY) {
                txtSeg.setText("");
                txtTer.setText("");
                txtQua.setText("1");
                txtQui.setText("2");
                txtSex.setText("3");
                txtSab.setText("4");
                txtDom.setText("5");
                lastDayMonthInsertInText = 5;
            } else if (firstDayWeek == Calendar.THURSDAY) {
                txtSeg.setText("");
                txtTer.setText("");
                txtQua.setText("");
                txtQui.setText("1");
                txtSex.setText("2");
                txtSab.setText("3");
                txtDom.setText("4");
                lastDayMonthInsertInText = 4;
            } else if (firstDayWeek == Calendar.FRIDAY) {
                txtSeg.setText("");
                txtTer.setText("");
                txtQua.setText("");
                txtQui.setText("");
                txtSex.setText("1");
                txtSab.setText("2");
                txtDom.setText("3");
                lastDayMonthInsertInText = 3;
            } else if (firstDayWeek == Calendar.SATURDAY) {
                txtSeg.setText("");
                txtTer.setText("");
                txtQua.setText("");
                txtQui.setText("");
                txtSex.setText("");
                txtSab.setText("1");
                txtDom.setText("2");
                lastDayMonthInsertInText = 2;
            }
            isFull = true;
        }

        public void putSecondLine() {
            lastDayMonthInsertInText++;
            txtSeg.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtTer.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtQua.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtQui.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtSex.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtSab.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtDom.setText(String.valueOf(lastDayMonthInsertInText));
        }

        public void putThirdLine() {
            lastDayMonthInsertInText++;
            txtSeg.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtTer.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtQua.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtQui.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtSex.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtSab.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtDom.setText(String.valueOf(lastDayMonthInsertInText));
        }

        public void putFourthLine() {
            lastDayMonthInsertInText++;
            txtSeg.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtTer.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtQua.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtQui.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtSex.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtSab.setText(String.valueOf(lastDayMonthInsertInText));
            lastDayMonthInsertInText++;
            txtDom.setText(String.valueOf(lastDayMonthInsertInText));
        }

        public void putFifthLine() {
            txtSeg.setText("");
            txtTer.setText("");
            txtQua.setText("");
            txtQui.setText("");
            txtSex.setText("");
            txtSab.setText("");
            txtDom.setText("");

            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtSeg.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtTer.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtQua.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtQui.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtSex.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtSab.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtDom.setText(String.valueOf(lastDayMonthInsertInText));
        }

        public void putSexthdLine() {
            txtSeg.setText("");
            txtTer.setText("");
            txtQua.setText("");
            txtQui.setText("");
            txtSex.setText("");
            txtSab.setText("");
            txtDom.setText("");

            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtSeg.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtTer.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtQua.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtQui.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtSex.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtSab.setText(String.valueOf(lastDayMonthInsertInText));
            if (lastDayMonthInsertInText == currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return;

            lastDayMonthInsertInText++;
            txtDom.setText(String.valueOf(lastDayMonthInsertInText));
        }
    }
}
