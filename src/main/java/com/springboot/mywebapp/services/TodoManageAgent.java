package com.springboot.mywebapp.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.springboot.mywebapp.models.Todo;

public interface TodoManageAgent {
	List<Todo> findTodoByUserId(Long id);
	void addTodo(String description, String targetDate, String userName);
	void deleteTodo(Long id);
	void addTodo(String description, LocalDate targetDate, String userName);
	Todo getTodo(Long id);
	void saveTodo(Todo todo);
}
