package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    private final TaskRepository repository;

    public DbService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public Long countTasks() {
        return repository.count();
    }
}
