package com.example.Project.BackendProject.JwtDto;

import lombok.Data;

@Data
public class JwtRequest {
	private String user_name;
	private String user_password;
	
	public JwtRequest() {
		System.out.println("jwt Request");
	}
}
