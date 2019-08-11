package com.vbaslak.issuetracker.controller;

import com.vbaslak.issuetracker.domain.Comment;
import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.domain.Status;
import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
public class IssueController {
    @Autowired
    private IssueService issueService;

    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/issue")
    public String issue(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Issue> page = issueService.getPageIssue(filter, pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/issue");
        model.addAttribute("filter", filter);
        return "issue";
    }

    @GetMapping("/issue/{issue}")
    public String showIssueDetails(
            @PathVariable Issue issue,
            Model model
    ) {
        List<Status> statusList = new ArrayList<Status>(Arrays.asList(Status.values()));
        Set<Comment> comments = issue.getComments();
        model.addAttribute("issue", issue);
        model.addAttribute("statusList", statusList);
        model.addAttribute("comments", comments);
        return "addCommentIssue";
    }

    @GetMapping("/newIssue")
    public String newIssue(){
        return "newIssue";
    }

    @PostMapping("/newIssue")
    public String createIssue(
            @AuthenticationPrincipal User user,
            @Valid Issue issue,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("issue", issue);
            return "newIssue";
        }
        model.addAttribute("issue", null);
        issueService.createIssue(user, issue, file);
        return "redirect:/issue";
    }

    @PostMapping("/issue")
    public String createIssueComment(
            @AuthenticationPrincipal User user,
            @Valid Comment comment,
            BindingResult bindingResult,
            Model model,
            @RequestParam("issueId") Issue issue
    ){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return showIssueDetails(issue, model);
        }
        issueService.createIssueComment(user, comment, issue);
        return "redirect:/issue";
    }
}
