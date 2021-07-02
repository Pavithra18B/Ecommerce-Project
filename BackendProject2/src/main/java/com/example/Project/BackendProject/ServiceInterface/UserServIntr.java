package com.example.Project.BackendProject.ServiceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Project.BackendProject.Dto.UserRequest;
import com.example.Project.BackendProject.Model.User;

public interface UserServIntr {
	User addUser(UserRequest userRequest);
 
	User findById(int userId);

	Page<User> getUser(Pageable page);

	void delete(int userId);

	List findAll();

	User updateUser(int userId, UserRequest userRequest) throws Exception;
}
