package com.test.zorii;

import com.test.zorii.enums.TaskStatus;
import com.test.zorii.exceptions.TaskNotFoundException;
import com.test.zorii.model.Task;
import com.test.zorii.repository.TaskRepository;
import com.test.zorii.service.TaskService;
import com.test.zorii.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = TaskServiceImpl.class)
class TaskServiceTest {
	@MockBean
	private TaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	@Test
	void getTaskByIdShouldReturnTaskWhenFound() {
		Task task = Task.builder()
				.id(1L)
				.name("name")
				.description("some description")
				.status(TaskStatus.IN_PROGRESS)
				.build();

		Mockito.when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

		Task taskById = taskService.getTaskById(task.getId());

		assertEquals(task, taskById);
	}

	@Test
	void getTaskByIdShouldThrowExceptionWhenNotFound() {
		Mockito.when(taskRepository.findById(Mockito.any(Long.class))).thenThrow(new TaskNotFoundException());

		assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(Mockito.any(Long.class)));
	}

	@Test
	void deleteTaskByIdShouldDeleteTaskWhenFound() {
		Task task = Task.builder()
				.id(1L)
				.name("name")
				.description("some description")
				.status(TaskStatus.IN_PROGRESS)
				.build();

		Mockito.when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

		taskService.deleteById(task.getId());

		verify(taskRepository).deleteById(task.getId());
	}

	@Test
	void deleteTaskByIdShouldThrowExceptionWhenNotFound() {
		Mockito.when(taskRepository.findById(Mockito.any(Long.class))).thenThrow(new TaskNotFoundException());

		assertThrows(TaskNotFoundException.class, () -> taskService.deleteById(Mockito.any(Long.class)));
	}

	@Test
	void createTaskShouldReturnCreatedTask() {
		Task task = Task.builder()
				.id(1L)
				.name("name")
				.description("some description")
				.status(TaskStatus.IN_PROGRESS)
				.build();

		Mockito.when(taskRepository.save(task)).thenReturn(task);

		Task savedTask = taskService.save(task);

		assertEquals(task.getId(), savedTask.getId());
		assertEquals(task.getName(), savedTask.getName());
		assertEquals(task.getDescription(), savedTask.getDescription());
		assertEquals(task.getStatus(), savedTask.getStatus());
	}

	@Test
	void getAllTaskShouldReturnListOfTask() {
		List<Task> tasks = Arrays.asList(new Task(), new Task());

		Mockito.when(taskRepository.findAll()).thenReturn(tasks);

		List<Task> taskList = taskService.getAllTask();

		assertEquals(tasks.size(), taskList.size());
	}

	@Test
	void updateTaskShouldReturnUpdatedTask() {
		Task task = Task.builder()
				.id(1L)
				.name("name")
				.description("some description")
				.status(TaskStatus.IN_PROGRESS)
				.build();

		Mockito.when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

		task.setName("anotherName");

		Mockito.when(taskRepository.save(task)).thenReturn(task);

		Task updateTask = taskService.updateTask(task);

		assertEquals(updateTask.getName(),task.getName());
	}
}

