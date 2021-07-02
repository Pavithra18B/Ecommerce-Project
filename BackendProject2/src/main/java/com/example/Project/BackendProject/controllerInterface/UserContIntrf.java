package com.example.Project.BackendProject.controllerInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Project.BackendProject.Dto.ApiResponse;
import com.example.Project.BackendProject.Dto.UserRequest;
import com.example.Project.BackendProject.Model.User;

public interface UserContIntrf {
	User addUser(UserRequest userRequest);

	List listUser();
 
	ResponseEntity<ApiResponse> getOne(int userId);

	User deleteUser(int userId);

}
