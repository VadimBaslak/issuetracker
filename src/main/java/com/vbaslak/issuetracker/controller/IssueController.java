package com.vbaslak.issuetracker.controller;

import com.vbaslak.issuetracker.domain.Comment;
import com.vbaslak.issuetracker.domain.Issue;
import com.vbaslak.issuetracker.domain.Status;
import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.repos.CommentRepository;
import com.vbaslak.issuetracker.repos.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.UUID;

@Controller
public class IssueController {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/newIssue")
    public String newIssue(){
        return "newIssue";
    }

    @PostMapping("/newIssue")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String issueName,
            @RequestParam String description,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Issue issue = new Issue(issueName, user, description);

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

        issueRepository.save(issue);
        return "redirect:/issues";
    }



    @GetMapping("/issue/{issue}")
    public String ChangeIssue(
            @PathVariable Issue issue,
            Model model
    ) {
        List<Status> statusList = new ArrayList<Status>(Arrays.asList(Status.values()));
        Set<Comment> comments = issue.getComments();

        model.addAttribute("issue", issue);
        model.addAttribute("statusList", statusList);
        model.addAttribute("comments", comments);
        return "changeIssue";
    }

    @PostMapping("/issue")
    public String statusSave(
            @AuthenticationPrincipal User user,
            @RequestParam("status") String status,
            @RequestParam("textComment") String textComment,
            @RequestParam("issueId") Issue issue
    ){
        issue.setStatus(status);
        Comment comment = new Comment(issue.getStatus(), textComment, user, issue);
        issueRepository.save(issue);
        commentRepository.save(comment);
        return "redirect:/issues";
    }
}
