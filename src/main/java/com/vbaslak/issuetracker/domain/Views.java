package com.vbaslak.issuetracker.domain;

public final class Views {
    //issue interfaces:
    public interface IssueId{}
    public interface IssueName{}
    public interface IssueDescription{}
    public interface IssueStartDate{}
    public interface IssueStatus{}
    public interface IssueAuthor{}
    public interface IssueFileName{}
    public interface IssueComments{}

    //comment interfaces:
    public interface CommentId{}
    public interface CommentText{}
    public interface CommentStatus{}
    public interface CommentDate{}
    public interface CommentAuthor{}

    //user interfaces:
    public interface UserId {}
    public interface UserName {}
    public interface UserPassword {}
    public interface UserActive {}
    public interface UserRoles {}

    //composite interfaces:
    public interface BasicInformationIssue extends IssueId, IssueName, IssueStatus, IssueStartDate{}
    public interface FullInformationComment extends CommentId, CommentText, CommentStatus, CommentDate, CommentAuthor{}
    public interface FullInformationIssue extends BasicInformationIssue, IssueDescription, IssueAuthor, IssueFileName, IssueComments, UserName, FullInformationComment{}

}
