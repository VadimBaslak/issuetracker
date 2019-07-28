package com.vbaslak.issuetracker.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Issue{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String issueName;
    private String description;
    private Date startDate;
    private String status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    @OneToMany(mappedBy = "issueWithComments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Issue() {}

    public Issue(String name, User user, String description) {
        this.issueName = name;
        this.author = user;
        this.description = description;

        this.startDate = new Date();
        this.status = Status.CREATED.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public String getStatus() {
        return status != null ? status : Status.CLOSED.getStatus();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
