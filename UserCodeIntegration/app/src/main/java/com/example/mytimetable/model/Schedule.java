package com.example.mytimetable.model;

public class Schedule {
    private int id;
    private String day;
    private String time;
    private String subject;

    public Schedule(int id, String day, String time, String subject) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.subject = subject;
    }

    public int getId() { return id; }
    public String getDay() { return day; }
    public String getTime() { return time; }
    public String getSubject() { return subject; }
}
