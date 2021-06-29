package com.example.Project.BackendProject.controllerInterface;

import java.util.List;

import com.example.Project.BackendProject.Dto.UserRequest;
import com.example.Project.BackendProject.Model.User;

public interface UserContIntrf {
	public User addUser(UserRequest userRequest);
	public List listUser();
	  public User getOne(int userId);
	  public User deleteUser(int userId);

}
