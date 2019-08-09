package com.vbaslak.issuetracker.repository;

import com.vbaslak.issuetracker.domain.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long>  {
    Page<Issue> findAll(Pageable pageable);
    Page<Issue> findByIssueName(String issueName, Pageable pageable);
}
