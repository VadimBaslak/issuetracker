package com.vbaslak.issuetracker.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vbaslak.issuetracker.api.exception.NotFoundException;
import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.domain.Views;
import com.vbaslak.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("api/issue")
@RestController
public class IssueControllerRest {
    @Autowired
    private IssueService issueService;

    @GetMapping
    @JsonView(Views.BasicInformationIssue.class)
    public List<Issue> getIssue(){
        return issueService.getIssues();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullInformationIssue.class)
    public Issue showIssueDetails(@PathVariable("id") Issue issue) {
        if (issue == null){
            throw new NotFoundException();
        }
        return issue;
    }

    @PostMapping
    @JsonView(Views.BasicInformationIssue.class)
    public Issue createIssue (
            @AuthenticationPrincipal User user,
            @RequestBody String issueName,
            @RequestBody String description
            //@RequestBody MultipartFile file
    ) throws IOException {
        Issue issue = new Issue(issueName, user, description);
        return showIssueDetails(issueService.createIssue(user, issue, null));
    }

    @PutMapping("{id}")
    public Issue updateIssueName(@PathVariable("id") Issue issueFromDb, @RequestBody String issueName) {
        return issueService.updateIssueName(issueFromDb, issueName);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Issue issue) {
        issueService.delete(issue);
    }

}
