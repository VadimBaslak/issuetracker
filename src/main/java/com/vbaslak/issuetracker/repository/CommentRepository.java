package com.vbaslak.issuetracker.repository;

import com.vbaslak.issuetracker.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

