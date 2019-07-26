package com.vbaslak.issuetracker.controller;

import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.repos.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class IssueController {
    @Autowired
    private IssueRepository issueRepository;

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

    @GetMapping("/issue/{issue}")
    public String ChangeIssue(@PathVariable Issue issue, Model model) {
        model.addAttribute("issue", issue);
        return "changeIssue";
    }
}
