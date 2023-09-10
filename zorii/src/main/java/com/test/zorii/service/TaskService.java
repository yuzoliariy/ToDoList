package com.test.zorii.service;

import com.test.zorii.model.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task);

    List<Task> getAllTask();

    Task getTaskById(Long id);

    Task updateTask(Task task);

    void deleteById(Long id);

}
