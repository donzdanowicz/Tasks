package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @Mock
    DbService dbService;

    @Mock
    TaskRepository repository;

    @Test
    void getAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1L, "Task", "New Task");
        Task task2 = new Task(2L, "Task2", "New Task 2");
        taskList.add(task1);
        taskList.add(task2);

        //When
        when(dbService.getAllTasks()).thenReturn(taskList);

        //Then
        assertEquals(2, dbService.getAllTasks().size());
    }

    @Test
    void getTask() {
        //Given
        Task task1 = new Task(1L, "Task", "New Task");

        //When
        when(dbService.getTask(1L)).thenReturn(Optional.of(task1));

        //Then
        assertEquals(Optional.of(task1), dbService.getTask(1L));
    }

    @Test
    void deleteById() {
        //Given
        Task task1 = new Task(1L, "Task", "New Task");

        //When
        dbService.deleteById(1L);

        //Then
        assertEquals(Optional.empty(), dbService.getTask(1L));
    }

    @Test
    void saveTask() {
        //Given
        Task task = new Task(1L, "Task", "New Task");

        //When
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        Task updatedTask = dbService.getTask(1L).orElseThrow();
        updatedTask.setContent("Updated Task");
        dbService.saveTask(updatedTask);

        //Then
        assertEquals("Updated Task", updatedTask.getContent());
    }
}