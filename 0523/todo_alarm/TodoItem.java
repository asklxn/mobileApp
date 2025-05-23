package com.example.todo;
public class TodoItem {
    String task;
    String date;
    String time;
    public TodoItem(String task, String date, String time) {
        this.task = task;
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }
}
