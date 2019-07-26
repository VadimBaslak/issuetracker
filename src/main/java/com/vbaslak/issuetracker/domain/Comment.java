package com.vbaslak.issuetracker.domain;

import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Status status;
    private Date date;
    private User user;
    private String comment;

    private Issue issue;


    public Comment(){}

    public Comment(String status, User user, String string) {
        this.status = Status.valueOf(status);
        this.date = new Date();
        this.user = user;
        this.comment = string;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getString() {
        return comment;
    }

    public void setString(String comment) {
        this.comment = comment;
    }
}
