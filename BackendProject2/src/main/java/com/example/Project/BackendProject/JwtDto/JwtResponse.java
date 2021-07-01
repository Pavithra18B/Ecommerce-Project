package com.example.Project.BackendProject.JwtDto;

import com.example.Project.BackendProject.Model.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class JwtResponse {
	private User user;
	private String jwtToken;

	public JwtResponse(User user, String jwtToken) {
		log.info("jwt response");
		this.user = user;
		this.jwtToken = jwtToken;
	}

}
