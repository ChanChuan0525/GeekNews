package com.chanchuan.geeknews;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.chanchuan.Utils.DataUtils;
import com.chanchuan.base.BaseActivity;
import com.chanchuan.eventbus.CalendarEvent;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;

public class CalendarActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.calender)
    MaterialCalendarView calender;

    private CalendarDay calendarDay;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(toolBar);
        setTitle("日历");
        calender.state().edit().setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2008, 8, 8))
                .setMaximumDate(CalendarDay.from(DataUtils.getCurrentYear(), DataUtils.getCurrentMonth(), DataUtils.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calender.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                calendarDay = date;

                /**
                 * 发送选中日期
                 */
                String month = calendarDay.getMonth() + 1 + "";
                month = month.length() == 1 ? "0" + month : month;
                String day = calendarDay.getDay() + "";
                day = day.length() == 1 ? "0" + day : day;
                EventBus.getDefault().post(new CalendarEvent(calendarDay.getYear() + "" + month + day));
                finish();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_calendar;
    }

}
