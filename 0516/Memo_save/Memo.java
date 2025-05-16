package com.example.memo;

public class Memo {
    private String text;
    private String date;

    public Memo(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
