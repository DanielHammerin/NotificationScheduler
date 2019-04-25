package com.example.notificationscheduler;

public class Notification {

    private String Title;
    private String Date;
    private String Time;

    public Notification(String title, String date, String time) {
        this.Title = title;
        this.Date = date;
        this.Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
