package com.vbaslak.issuetracker.repository;

import com.vbaslak.issuetracker.domain.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long>  {
    List<Issue> findAll();
    Page<Issue> findAll(Pageable pageable);
    Page<Issue> findByIssueName(String issueName, Pageable pageable);
}
