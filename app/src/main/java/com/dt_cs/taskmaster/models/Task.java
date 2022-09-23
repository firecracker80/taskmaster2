package com.dt_cs.taskmaster.models;

public class Task {
    private String title;
    private String body;
    private Status status;

    public Task(String title, String body, Status status) {
        this.title = title;
        this.body = body;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
