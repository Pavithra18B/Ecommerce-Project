package com.example.Project.BackendProject.Dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String user_name;

	private String first_name;

	private String last_name;

	private String user_password;

	private String email_id;

	private Set<Integer> role;

}
