package com.vbaslak.issuetracker.repository;

import com.vbaslak.issuetracker.domain.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    //List<Issue> findByIssueName(String issueName);
    Page<Issue> findAll(Pageable pageable);
    Page<Issue> findByIssueName(String issueName, Pageable pageable);
}
