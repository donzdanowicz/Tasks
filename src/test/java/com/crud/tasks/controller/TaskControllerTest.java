package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @MockBean
    private TaskNotFoundException taskNotFoundException;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    /*@Test
    public void shouldThrowTaskNotFoundException() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "New Task", "New task's content");
        TaskDto taskDto = new TaskDto(1L, "New Task", "New task's content");

        //When
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(dbService.getTask(2L)).thenReturn(null);
        when(taskController.getTask(1L)).thenReturn(taskDto);
        when(taskController.getTask(2L)).thenReturn(null);

        //Then
        assertDoesNotThrow(()-> taskController.getTask(1L));
        assertThrows(Exception.class, () ->  taskController.getTask(2L));
        //assertThrows(TaskNotFoundException.class, () ->  taskController.getTask(2L));
    }*/

    @Test
    public void shouldGetEmptyListOfTasks() throws Exception {
        //Given
        when(taskController.getTasks()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetTasks() throws Exception {
        // Given
        List<TaskDto> taskLists = List.of(new TaskDto(1L, "New task", "New task's content"));
        when(taskController.getTasks()).thenReturn(taskLists);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("New task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content",
                        Matchers.is("New task's content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        List<TaskDto> taskLists = List.of(new TaskDto(1L, "New task", "New task's content"));

        //When
        taskController.deleteTask(1L);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(2L, "New task", "New task's content");
        TaskDto updatedTaskDto = new TaskDto(2L, "New task", "Updated content");

        when(taskController.updateTask(any(TaskDto.class))).thenReturn(updatedTaskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTaskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("New task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Updated content")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(3L, "New task", "New task's new content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("New task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("New task's new content")));
    }
}