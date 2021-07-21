package com.example.Project.BackendProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.JwtDto.JwtRequest;
import com.example.Project.BackendProject.JwtDto.JwtResponse;
import com.example.Project.BackendProject.Service.JwtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class JwtController {
	@Autowired
	private JwtService jwtService;

	public JwtController() {
		log.info("Jwt auth");
	}

	@PostMapping("/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		log.info("create token service");
		return jwtService.createJwtToken(jwtRequest);
	}
}
