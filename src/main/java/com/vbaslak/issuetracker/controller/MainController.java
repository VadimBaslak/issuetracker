package com.vbaslak.issuetracker.controller;

import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.repos.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String MainPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Issue> issues = issueRepository.findAll();

        if(filter != null && !filter.isEmpty()) {
            issues = issueRepository.findByIssueName(filter);
        } else {
            issues = issueRepository.findAll();
        }

        model.addAttribute("issues", issues);
        model.addAttribute("filter", filter);

        return "main";
    }

    @GetMapping("/newIssue")
    public String newIssue(){
        return "newIssue";
    }

    @PostMapping("/newIssue")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String issueName,
            @RequestParam String description,
            Map<String, Object> model
    ){
        Issue issue = new Issue(issueName, user, description);
        issueRepository.save(issue);
        return "redirect:/main";
    }
}