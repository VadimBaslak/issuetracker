package com.vbaslak.issuetracker.domain;

import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String textComment;
    private String status;
    private Date dateComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User commentAuthor;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
