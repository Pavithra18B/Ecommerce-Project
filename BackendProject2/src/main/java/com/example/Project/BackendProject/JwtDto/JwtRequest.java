package com.example.Project.BackendProject.JwtDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class JwtRequest {
	private String user_name;
	private String user_password;

	public JwtRequest() {
		log.info("jwt Request");
	}
}
