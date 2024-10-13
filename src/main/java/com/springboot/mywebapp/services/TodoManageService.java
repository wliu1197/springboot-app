package com.springboot.mywebapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.springboot.mywebapp.models.Todo;
import com.springboot.mywebapp.models.User;
import com.springboot.mywebapp.repositories.TodoJpaRepository;
import com.springboot.mywebapp.repositories.UserJpaRepository;

@Service
public class TodoManageService implements TodoManageAgent{
	@Autowired
	private TodoJpaRepository toDoRepository;
	@Autowired
	private UserJpaRepository userRepository;
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	public List<Todo> findTodoByUserId(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			if(user.getCancelled() == null) {
			    return toDoRepository.findTodoByUserId(user.getId());
			}
		}
		return null;	
	}
	
	@Override
	public void addTodo(String description, String targetDate, String userName) {
		User user = authenticationService.findUserByName(userName);
		
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(targetDate, dateformatter);
	//	LocalDateTime dateTime = ld.atStartOfDay();
		toDoRepository.save(new Todo(description, ld, false, user));
	}
	
	@Override
	public void addTodo(String description, LocalDate targetDate, String userName) {
		User user = authenticationService.findUserByName(userName);
		
//		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate ld = LocalDate.parse(targetDate, dateformatter);
//		LocalDateTime dateTime = targetDate.atStartOfDay();
		toDoRepository.save(new Todo(description, targetDate, false, user));
	}
	
	@Override
	public void deleteTodo(Long id) {
		toDoRepository.deleteById(id);
	}
	
	@Override
	public Todo getTodo(Long id) {
		return toDoRepository.findById(id).orElse(null);
	}
	

	@Override
	public void saveTodo(Todo todo) {
		toDoRepository.save(todo);
	}
		
}
