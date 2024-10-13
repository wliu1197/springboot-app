package com.springboot.mywebapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.mywebapp.models.Todo;
import com.springboot.mywebapp.models.User;

public interface TodoJpaRepository  extends JpaRepository<Todo, Long>{
	@Query("FROM todo t WHERE t.user.id = ?1")
	List<Todo> findTodoByUserId(Long userId);
}
