package com.example.Project.BackendProject.Repository;


import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.example.Project.BackendProject.Model.BaseRepository;
import com.example.Project.BackendProject.Model.User;



@Repository
public interface UserRepo extends BaseRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.user_name = ?1")
	User findByUserName(String user_name);

}