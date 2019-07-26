package com.vbaslak.issuetracker.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Issue{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String issueName;
    private String description;
    private Date startDate;

//    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "issue_status", joinColumns = @JoinColumn(name = "issue_id"))
//    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    //    private List<Comment> comments = new ArrayList<Comment>();

    public Issue() {}

    public Issue(String name, User user, String description) {
        this.issueName = name;
        this.author = user;
        this.description = description;
        this.startDate = new Date();
        this.status = Status.CREATED;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
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
