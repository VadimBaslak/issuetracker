package com.vbaslak.issuetracker.service;

import com.vbaslak.issuetracker.domain.Comment;
import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.domain.Status;
import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.repository.CommentRepository;
import com.vbaslak.issuetracker.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public Page<Issue> getPageIssue(String filter, Pageable pageable){
        if(filter != null && !filter.isEmpty()) {
            return issueRepository.findByIssueName(filter, pageable);
        } else {
            return issueRepository.findAll(pageable);
        }
    }

    public List<Issue> getIssues(){
        return issueRepository.findAll();
    }

    public Issue createIssue(User user, Issue issue, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            issue.setFilename(resultFilename);
        }
        issue.setAuthor(user);
        issue.setStartDate(new Date());
        issue.setStatus(Status.CREATED.getStatus());
        issueRepository.save(issue);
        return issue;
    }

    public void createIssueComment(User user, Comment comment, Issue issue){
        comment.setCommentAuthor(user);
        comment.setIssueWithComments(issue);
        comment.setDateComment(new Date());
        issue.setStatus(comment.getStatus());
        issueRepository.save(issue);
        commentRepository.save(comment);
    }

    public Issue updateIssueName(Issue issue, String issueName) {
        issue.setIssueName(issueName);
        issueRepository.save(issue);
        return issue;
    }

    public void delete(Issue issue) {
        issueRepository.delete(issue);
    }
}
