package com.chanchuan.eventbus;

public class CalendarEvent {
    String date;

    public CalendarEvent(String date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}

