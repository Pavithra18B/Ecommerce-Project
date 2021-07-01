package com.example.Project.BackendProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.example.Project.BackendProject.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	User findByUserName(String userName);

}
