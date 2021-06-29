package com.example.Project.BackendProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.JwtDto.JwtRequest;
import com.example.Project.BackendProject.JwtDto.JwtResponse;
import com.example.Project.BackendProject.Service.JwtService;
@RestController
public class JwtController {
	@Autowired
	private JwtService jwtService;
	
	public JwtController() {
		System.out.println("Jwt auth");
	}

	@PostMapping("/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println("create token service");
		return jwtService.createJwtToken(jwtRequest);
	}
}
