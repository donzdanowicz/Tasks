package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskController taskController;

    @MockBean
    private TaskNotFoundException taskNotFoundException;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldThrowTaskNotFoundException() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "New Task", "New task's content");
        // taskNotFoundException = new TaskNotFoundException();
        //When

        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(dbService.getTask(2L)).thenReturn(null);

        //Then
        assertDoesNotThrow(()-> taskController.getTask(1L));
        assertThrows(Exception.class, () ->  taskController.getTask(2L));
        //assertThrows(taskNotFoundException.getClass(), () ->  taskController.getTask(2L));

    }
}