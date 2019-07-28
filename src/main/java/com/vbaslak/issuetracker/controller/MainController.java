package com.vbaslak.issuetracker.controller;

import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.repos.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/issues")
    public String issuesPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Issue> issuesN = issueRepository.findAll();

        if(filter != null && !filter.isEmpty()) {
            issuesN = issueRepository.findByIssueName(filter);
        } else {
            issuesN = issueRepository.findAll();
        }
        /*Later: The List has been added so that the issues that were changed were at the top of the list.
        No solution has yet been found without an additional entity.*/
        List<Issue> issues = new ArrayList<>();
        Collections.reverse(issues);
        for (Issue issue: issuesN) {
            issues.add(0, issue);
        }
        model.addAttribute("issues", issues);
        model.addAttribute("filter", filter);

        return "issues";
    }





}