package com.vbaslak.issuetracker.controller;

import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.repos.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<Issue> issues;
        if(filter != null && !filter.isEmpty()) {
            issues = issueRepository.findByIssueName(filter);
        } else {
            issues = issueRepository.findAll();
        }
        Collections.reverse(issues);
        model.addAttribute("issues", issues);
        model.addAttribute("filter", filter);

        return "issues";
    }





}