package com.vbaslak.issuetracker.repos;

import com.vbaslak.issuetracker.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByIssueName(String issueName);
}
