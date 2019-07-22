package com.vbaslak.issuetracker.domain;

import java.util.Date;

public class Comment {
    private Status status;
    private Date date;
    private User user;
    private String string;

    public Comment(){}

    public Comment(String status, User user, String string) {
        this.status = Status.valueOf(status);
        this.date = new Date();
        this.user = user;
        this.string = string;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public Date getData() {
        return date;
    }

    public void setData(Date data) {
        this.date = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
