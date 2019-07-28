package com.vbaslak.issuetracker.repos;

import com.vbaslak.issuetracker.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByTextComment(String textComment);
}

