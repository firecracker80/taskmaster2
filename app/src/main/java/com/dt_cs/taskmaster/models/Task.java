package com.dt_cs.taskmaster.models;

import androidx.annotation.NonNull;
import java.util.Date;

public class Task {

    public Long id;
    private String title;
    private String body;
    private Status status;
    private java.util.Date dateCreated;

    public Task(String title, String body, Status status, Date dateCreated) {
        this.title = title;
        this.body = body;
        this.status = status;
        this.dateCreated = dateCreated;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public enum Status {
        NEWTASK("newTask"),
        ASSIGNED("assigned"),
        INPROGRESS("inProgress"),
        COMPLETE("complete");

        private final String taskType;

        Status(String _taskType){
        this.taskType = _taskType;
    }

        public String getTaskType(){
            return taskType;
        }
        public static Status fromString(String possibleTaskType){
        for(Status type : Status.values()){
        if(type.taskType.equals(possibleTaskType)){
            return type;
            }
        }

        return null;
    }
    @NonNull
    @Override
    public String toString(){
        if(taskType == null){
            return "";
        }
        return taskType;
    }
    }
}
