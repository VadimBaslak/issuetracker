package com.vbaslak.issuetracker.repos;

import com.vbaslak.issuetracker.domain.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    List<Issue> findByIssueName(String issueName);
}
