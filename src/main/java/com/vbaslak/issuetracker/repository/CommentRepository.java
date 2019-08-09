package com.vbaslak.issuetracker.repository;

import com.vbaslak.issuetracker.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTextComment(String textComment);
}

