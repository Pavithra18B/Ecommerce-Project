package com.example.Project.BackendProject.Dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String userName;

	private String firstName;

	private String lastName;

	private String userPassword;

	private String emailId;

	private Set<Integer> role;

}
