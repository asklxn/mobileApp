package com.example.todo;
public class TodoItem {
    String task;
    String date;

    public TodoItem(String task, String date) {
        this.task = task;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }
}
