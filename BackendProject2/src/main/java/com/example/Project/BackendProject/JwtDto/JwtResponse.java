package com.example.Project.BackendProject.JwtDto;

import com.example.Project.BackendProject.Model.User;

import lombok.Data;

@Data
public class JwtResponse {
	private User user;
	private String jwtToken;
	
	public JwtResponse(User user, String jwtToken) {

		System.out.println("jwt response");

		this.user = user;
		this.jwtToken = jwtToken;
	}

}
