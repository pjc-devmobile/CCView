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
import com.navan.customcalendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomCalendarView extends LinearLayout {

    private ViewHolder vh;
    private Calendar dateSelected;
    private Calendar minDate = null, maxDate = null;
    private Calendar currentCalendar;
    private int lastDayMonthInsertInText;

    private List<Calendar> datesDisables = null;
    private List<Calendar> datesEnables = null;
    private List<Integer> daysWeekDisbales = null;

    private OnDaySelectedListener onDaySelectedListener;

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
        setCurrentDate(Calendar.getInstance());
    }

    public void setCurrentDate(Calendar currentCalendar) {
        this.currentCalendar = currentCalendar;
        showCurrentDate();
    }

    public void setDateSelected(Calendar dateSelected) {
        this.dateSelected = dateSelected;
        showCurrentDate();
    }

    public void setMaxDate(Calendar maxDate) {
        this.maxDate = maxDate;
        disableDatesFuture();
    }

    public void setMinDate(Calendar minDate) {
        this.minDate = minDate;
        disableDatesPast();
    }

    public void setOnDaySelectedListener(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    //AINDA NÃO FUNCIONA
    public void setSpecialDatesDisables(List<Calendar> datesDisables) {
        this.datesDisables = datesDisables;
        showCurrentDate();
    }

    public void setSpecialDatesEnables(List<Calendar> datesEnables) {
        this.datesEnables = datesEnables;
        showCurrentDate();
    }

    /**
     * @param daysWeekDisbales 1 = domingo
     *                         2 = segunda
     *                         ...
     */
    public void setDaysWeekDisbales(List<Integer> daysWeekDisbales) {
        this.daysWeekDisbales = daysWeekDisbales;
        showCurrentDate();
    }

    private void showCurrentDate() {
        if (currentCalendar == null)
            currentCalendar = Calendar.getInstance();

        //primeira semana
        Calendar c = Calendar.getInstance();
        c.setTime(currentCalendar.getTime());
        c.set(Calendar.DAY_OF_MONTH, 1);
        vh.line1.putFirstWeek(c.get(Calendar.DAY_OF_WEEK));

        vh.line2.putSecondLine();
        vh.line3.putThirdLine();
        vh.line4.putFourthLine();
        vh.line5.putFifthLine();
        vh.line6.putSexthdLine();

        vh.txtCurrentDate.setText(DateUtils.formatDateExtensoMesAno(currentCalendar.getTime()));

        if (dateSelected == null)
            vh.txtDateSelected.setText(DateUtils.formatDateExtenso(currentCalendar.getTime()));
        else
            vh.txtDateSelected.setText(DateUtils.formatDateExtenso(dateSelected.getTime()));

        disableDaysWeek();
        enableDaysSpecial();

        showPreDateSelected();
    }

    private void disableDaysWeek() {
        //disables days week
        if (daysWeekDisbales != null) {
            if (daysWeekDisbales.contains(new Integer(1))) {
                vh.line1.txtDom.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line1.txtDom.setEnabled(false);

                vh.line2.txtDom.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line2.txtDom.setEnabled(false);

                vh.line3.txtDom.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line3.txtDom.setEnabled(false);

                vh.line4.txtDom.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line4.txtDom.setEnabled(false);

                vh.line5.txtDom.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line5.txtDom.setEnabled(false);

                vh.line6.txtDom.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line6.txtDom.setEnabled(false);
            }

            if (daysWeekDisbales.contains(new Integer(2))) {
                vh.line1.txtSeg.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line1.txtSeg.setEnabled(false);

                vh.line2.txtSeg.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line2.txtSeg.setEnabled(false);

                vh.line3.txtSeg.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line3.txtSeg.setEnabled(false);

                vh.line4.txtSeg.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line4.txtSeg.setEnabled(false);

                vh.line5.txtSeg.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line5.txtSeg.setEnabled(false);

                vh.line6.txtSeg.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line6.txtSeg.setEnabled(false);
            }

            if (daysWeekDisbales.contains(new Integer(3))) {
                vh.line1.txtTer.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line1.txtTer.setEnabled(false);

                vh.line2.txtTer.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line2.txtTer.setEnabled(false);

                vh.line3.txtTer.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line3.txtTer.setEnabled(false);

                vh.line4.txtTer.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line4.txtTer.setEnabled(false);

                vh.line5.txtTer.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line5.txtTer.setEnabled(false);

                vh.line6.txtTer.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line6.txtTer.setEnabled(false);
            }

            if (daysWeekDisbales.contains(new Integer(4))) {
                vh.line1.txtQua.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line1.txtQua.setEnabled(false);

                vh.line2.txtQua.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line2.txtQua.setEnabled(false);

                vh.line3.txtQua.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line3.txtQua.setEnabled(false);

                vh.line4.txtQua.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line4.txtQua.setEnabled(false);

                vh.line5.txtQua.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line5.txtQua.setEnabled(false);

                vh.line6.txtQua.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line6.txtQua.setEnabled(false);
            }

            if (daysWeekDisbales.contains(new Integer(5))) {
                vh.line1.txtQui.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line1.txtQui.setEnabled(false);

                vh.line2.txtQui.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line2.txtQui.setEnabled(false);

                vh.line3.txtQui.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line3.txtQui.setEnabled(false);

                vh.line4.txtQui.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line4.txtQui.setEnabled(false);

                vh.line5.txtQui.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line5.txtQui.setEnabled(false);

                vh.line6.txtQui.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line6.txtQui.setEnabled(false);
            }

            if (daysWeekDisbales.contains(new Integer(6))) {
                vh.line1.txtSex.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line1.txtSex.setEnabled(false);

                vh.line2.txtSex.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line2.txtSex.setEnabled(false);

                vh.line3.txtSex.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line3.txtSex.setEnabled(false);

                vh.line4.txtSex.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line4.txtSex.setEnabled(false);

                vh.line5.txtSex.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line5.txtSex.setEnabled(false);

                vh.line6.txtSex.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line6.txtSex.setEnabled(false);
            }

            if (daysWeekDisbales.contains(new Integer(7))) {
                vh.line1.txtSab.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line1.txtSab.setEnabled(false);

                vh.line2.txtSab.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line2.txtSab.setEnabled(false);

                vh.line3.txtSab.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line3.txtSab.setEnabled(false);

                vh.line4.txtSab.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line4.txtSab.setEnabled(false);

                vh.line5.txtSab.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line5.txtSab.setEnabled(false);

                vh.line6.txtSab.setTextColor(getResources().getColor(R.color.cinzaClaro));
                vh.line6.txtSab.setEnabled(false);
            }
        }

        showPreDateSelected();
    }

    private void disableDatesPast() {
        if (minDate == null)
            return;

        for (TextView tv : vh.textViews) {
            if (!tv.getText().toString().isEmpty()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentCalendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tv.getText().toString()));


                if (minDate != null)
                    if (calendar.before(minDate)) {
                        tv.setTextColor(getResources().getColor(R.color.cinzaClaro));
                        tv.setEnabled(false);
                    }
            }
        }

        showPreDateSelected();
    }

    private void disableDatesFuture() {
        if (maxDate == null)
            return;

        for (TextView tv : vh.textViews) {
            if (!tv.getText().toString().isEmpty()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentCalendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tv.getText().toString()));

                if (maxDate != null)
                    if (calendar.after(maxDate)) {
                        tv.setTextColor(getResources().getColor(R.color.cinzaClaro));
                        tv.setEnabled(false);
                    }
            }
        }

        showPreDateSelected();
    }

    private void enableDaysSpecial() {
        if (datesEnables == null || datesEnables.isEmpty())
            return;

        List<String> datesSpecialEnableString = new ArrayList<>();
        for (Calendar c : datesEnables)
            datesSpecialEnableString.add(DateUtils.formatDate(c.getTime()));

        int i = 0;
        for (TextView tv : vh.textViews) {
            if (!tv.getText().toString().isEmpty()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentCalendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tv.getText().toString()));


                if (datesSpecialEnableString.contains(DateUtils.formatDate(calendar.getTime()))) {
                    tv.setTextColor(getResources().getColor(R.color.preto));
                    tv.setEnabled(true);
                }

                if (dateSelected != null && dateSelected.equals(calendar.getTime())) {
                    tv.setTextColor(getResources().getColor(R.color.branco));
                    vh.views.get(i).setVisibility(VISIBLE);
                }
            }

            i++;
        }
        showPreDateSelected();
    }

    private void showPreDateSelected() {
        int i = 0;
        for (TextView tv : vh.textViews) {
            if (!tv.getText().toString().isEmpty()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentCalendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tv.getText().toString()));

                if (dateSelected != null && (DateUtils.formatDate(dateSelected.getTime()).equals(DateUtils.formatDate(calendar.getTime())))) {
                    tv.setTextColor(getResources().getColor(R.color.branco));
                    vh.views.get(i).setVisibility(VISIBLE);
                }
            }

            i++;
        }
    }

    //Classe das rederencias de views e OnClicks
    public class ViewHolder {

        private final ImageButton btnLeft, btnRight;
        private final TextView txtCurrentDate, txtDateSelected;
        private final LineNumbersDays line1, line2, line3, line4, line5, line6;

        private ClickNumberDay clickNumberDay = new ClickNumberDay();
        private ClickLeftRight clickLeftRight = new ClickLeftRight();

        private List<TextView> textViews = new ArrayList<>();
        private List<View> views = new ArrayList<>();

        public ViewHolder() {
            btnLeft = findViewById(R.id.btn_left);
            btnRight = findViewById(R.id.btn_right);

            txtCurrentDate = findViewById(R.id.txt_current_date);
            txtDateSelected = findViewById(R.id.txt_selected_date);

            line1 = new LineNumbersDays(findViewById(R.id.lay_number_days_1), clickNumberDay);
            line2 = new LineNumbersDays(findViewById(R.id.lay_number_days_2), clickNumberDay);
            line3 = new LineNumbersDays(findViewById(R.id.lay_number_days_3), clickNumberDay);
            line4 = new LineNumbersDays(findViewById(R.id.lay_number_days_4), clickNumberDay);
            line5 = new LineNumbersDays(findViewById(R.id.lay_number_days_5), clickNumberDay);
            line6 = new LineNumbersDays(findViewById(R.id.lay_number_days_6), clickNumberDay);

            btnRight.setOnClickListener(clickLeftRight);
            btnLeft.setOnClickListener(clickLeftRight);

            views.addAll(line1.getViews());
            views.addAll(line2.getViews());
            views.addAll(line3.getViews());
            views.addAll(line4.getViews());
            views.addAll(line5.getViews());
            views.addAll(line6.getViews());

            textViews.addAll(line1.getTextViews());
            textViews.addAll(line2.getTextViews());
            textViews.addAll(line3.getTextViews());
            textViews.addAll(line4.getTextViews());
            textViews.addAll(line5.getTextViews());
            textViews.addAll(line6.getTextViews());
        }

        private class ClickLeftRight implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                c.setTime(currentCalendar.getTime());

                if (v.getId() == R.id.btn_right) {
                    c.set(Calendar.MONTH, currentCalendar.get(Calendar.MONTH) + 1);
                    c.set(Calendar.DAY_OF_MONTH, 1);
                    if (maxDate != null) {
                        if (c.before(maxDate)) {
                            setCurrentDate(c);
                        }
                    } else {
                        setCurrentDate(c);
                    }

                } else if (v.getId() == R.id.btn_left) {
                    c.set(Calendar.MONTH, currentCalendar.get(Calendar.MONTH) - 1);
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

                    if (minDate != null) {
                        if (c.after(minDate)) {
                            setCurrentDate(c);
                        }
                    } else {
                        setCurrentDate(c);
                    }
                }

                //DATES PAST
                for (TextView tv : vh.textViews) {
                    tv.setTextColor(getResources().getColor(R.color.preto));
                    tv.setEnabled(true);
                }

                //hide tag in day selected
                for (int i = 0; i < textViews.size(); i++) {
                    views.get(i).setVisibility(GONE);
                    textViews.get(i).setTextColor(getResources().getColor(R.color.preto));
                }

                disableDatesPast();
                disableDatesFuture();
                disableDaysWeek();

                enableDaysSpecial();
            }
        }

        private class ClickNumberDay implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().isEmpty())
                    return;

                for (int i = 0; i < textViews.size(); i++) {
                    if (textViews.get(i).equals(v)) {
                        views.get(i).setVisibility(VISIBLE);
                        textViews.get(i).setTextColor(getResources().getColor(R.color.branco));
                    } else {
                        views.get(i).setVisibility(GONE);
                        textViews.get(i).setTextColor(getResources().getColor(R.color.preto));
                    }
                }

                currentCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(((TextView) v).getText().toString()));
                dateSelected = currentCalendar;
                vh.txtDateSelected.setText(DateUtils.formatDateExtenso(dateSelected.getTime()));

                disableDaysWeek();
                disableDatesPast();
                disableDatesFuture();
                enableDaysSpecial();

                if (onDaySelectedListener != null)
                    onDaySelectedListener.onDaySelected(dateSelected);
            }
        }
    }

    private class LineNumbersDays {
        private TextView txtSeg, txtTer, txtQua, txtQui, txtSex, txtSab, txtDom;
        private View viewSeg, viewTer, viewQua, viewQui, viewSex, viewSab, viewDom;

        public LineNumbersDays(View view, ViewHolder.ClickNumberDay clickNumberDay) {
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

            txtSeg.setOnClickListener(clickNumberDay);
            txtTer.setOnClickListener(clickNumberDay);
            txtQua.setOnClickListener(clickNumberDay);
            txtQui.setOnClickListener(clickNumberDay);
            txtSex.setOnClickListener(clickNumberDay);
            txtSab.setOnClickListener(clickNumberDay);
            txtDom.setOnClickListener(clickNumberDay);
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

        public List<View> getViews() {
            List<View> views = new ArrayList<>();

            views.add(viewDom);
            views.add(viewSeg);
            views.add(viewTer);
            views.add(viewQua);
            views.add(viewQui);
            views.add(viewSex);
            views.add(viewSab);

            return views;
        }

        public List<TextView> getTextViews() {
            List<TextView> textViews = new ArrayList<>();

            textViews.add(txtDom);
            textViews.add(txtSeg);
            textViews.add(txtTer);
            textViews.add(txtQua);
            textViews.add(txtQui);
            textViews.add(txtSex);
            textViews.add(txtSab);

            return textViews;
        }
    }

    public interface OnDaySelectedListener {
        void onDaySelected(Calendar dateSelected);
    }
}
