package com.test.zorii.service;

import com.test.zorii.exceptions.TaskNotFoundException;
import com.test.zorii.model.Task;
import com.test.zorii.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        Task savedTask = taskRepository.save(task);
        LOGGER.info("Task with id: " + savedTask.getId() + " successfully saved.");
        return savedTask;
    }

    @Override
    public List<Task> getAllTask() {
        List<Task> taskList = taskRepository.findAll();
        LOGGER.info("Retrieved " + taskList.size() + " tasks.");
        return taskList;
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> taskById = taskRepository.findById(id);
        if (taskById.isEmpty()) {
            String errorMessage = "Task with id: " + id + " not found.";
            LOGGER.error(errorMessage);
            throw new TaskNotFoundException(errorMessage);
        }
        LOGGER.info("Task with id: " + id + " found.");
        return taskById.get();
    }

    @Override
    public Task updateTask(Task task) {
        Task taskById = getTaskById(task.getId());
        taskById.setName(task.getName());
        taskById.setStatus(task.getStatus());
        taskById.setDescription(task.getDescription());
        taskRepository.save(taskById);
        LOGGER.info("Task with id: " + taskById.getId() + " successfully updated.");
        return taskById;
    }

    @Override
    public void deleteById(Long id) {
        Task taskById = getTaskById(id);
        taskRepository.deleteById(taskById.getId());
        LOGGER.info("Task with id: " + taskById.getId() + " successfully deleted.");
    }
}
