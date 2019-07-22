package com.vbaslak.issuetracker.repos;

import com.vbaslak.issuetracker.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByTaskName(String taskName);
}
