package com.vbaslak.issuetracker.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String taskName;
    private String description;
    private Status status;
    private Date startDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    //    private List<Comment> comments = new ArrayList<Comment>();

    public Task() {}

    public Task(String name, User user, String description) {
        this.taskName = name;
        this.author = user;
        this.description = description;
        this.startDate = new Date();
 //       this.status = Status.CREATED;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public Date getStartDate() {
        return startDate != null ? startDate : new Date();
    }

    public void setStartDate(Date startDate) {
        this.startDate = new Date();
    }

    public Status getStatus() {
        return status != null ? status : Status.CREATED;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    private static Set<String> set = Arrays.asList(Status.values())
//            .stream().map(s -> s.name()).collect(Collectors.toSet());
//
//    public static String getStatusString(String status) {
//        try {
//            return Status.valueOf(status).name();
//        } catch (IllegalArgumentException e) {
//            return "INVALID_STATUS";
//        }
//    }
}
