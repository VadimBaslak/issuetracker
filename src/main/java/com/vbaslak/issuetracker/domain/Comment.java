package com.vbaslak.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.CommentId.class)
    private Long id;

    @NotBlank(message = "Please fill out a text comment")
    @Length(max = 2048, message = "description too long (more than 2kB)")
    @JsonView(Views.CommentText.class)
    private String textComment;

    @JsonView(Views.CommentStatus.class)
    private String status;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.CommentDate.class)
    private Date dateComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonView(Views.CommentAuthor.class)
    private User commentAuthor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issue_id")
    private Issue issueWithComments;

    public Comment(){}

    public Comment(String status, String textComment, User commentAuthor, Issue issueWithComments) {
        this.status = status;
        this.textComment = textComment;
        this.commentAuthor = commentAuthor;
        this.issueWithComments = issueWithComments;

        this.dateComment = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public String getTextComment() {
        return textComment != null ? textComment : "not comment";
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public User getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(User commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public Issue getIssueWithComments() {
        return issueWithComments;
    }

    public void setIssueWithComments(Issue issueWithComments) {
        this.issueWithComments = issueWithComments;
    }
}
