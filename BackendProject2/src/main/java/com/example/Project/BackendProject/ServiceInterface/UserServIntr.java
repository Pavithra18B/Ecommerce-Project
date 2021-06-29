package com.example.Project.BackendProject.ServiceInterface;

import com.example.Project.BackendProject.Dto.UserRequest;
import com.example.Project.BackendProject.Model.User;

public interface UserServIntr {
	public User addUser(UserRequest userRequest);

	void delete(int id);
	User findById(int id);

}
