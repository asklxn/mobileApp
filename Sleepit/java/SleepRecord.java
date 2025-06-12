package com.example.sleepit;

public class SleepRecord {
    String date;
    String sleepTime;
    String wakeTime;

    public SleepRecord(String date, String sleepTime, String wakeTime) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
    }

    public String getDate() {
        return date;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }
}
