package com.vbaslak.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Issue{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.IssueId.class)
    private Long id;

    @NotBlank(message = "Please fill the Issue name")
    @Length(max = 255, message = "description too long (more than 255)")
    @JsonView(Views.IssueName.class)
    private String issueName;

    @NotBlank(message = "Please fill the description")
    @Length(max = 2048, message = "description too long (more than 2kB)")
    @JsonView(Views.IssueDescription.class)
    private String description;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.IssueStartDate.class)
    private Date startDate;

    @JsonView(Views.IssueStatus.class)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonView(Views.IssueAuthor.class)
    private User author;

    @JsonView(Views.IssueFileName.class)
    private String filename;

    @OneToMany(mappedBy = "issueWithComments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView(Views.IssueComments.class)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id.equals(issue.id);
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
        return status != null ? status : Status.CREATED.getStatus();
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
