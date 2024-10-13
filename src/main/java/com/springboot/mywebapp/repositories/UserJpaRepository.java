package com.springboot.mywebapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.mywebapp.models.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{
	List<User> findByUserName(String username);
	
	@Query("FROM admin_user u WHERE u.userName = ?1 and u.cancelled is null")
	List<User> findActiveUser(String username);
	
	@Query("FROM admin_user u WHERE u.cancelled is null")
	List<User> findAllActiveUser();
}
