package com.example.Project.BackendProject.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Jwt.JwtUtil;
import com.example.Project.BackendProject.JwtDto.JwtRequest;
import com.example.Project.BackendProject.JwtDto.JwtResponse;
import com.example.Project.BackendProject.Model.User;
import com.example.Project.BackendProject.Repository.UserRepo;

@Service
public class JwtService implements UserDetailsService{
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	// returning generated t0ken

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		System.out.println("getting user name (jwt service)");

		String user_name = jwtRequest.getUser_name();
		String user_password = jwtRequest.getUser_password();
		// authenticate(user_name, user_password);

		// UserDetails userDetails = loadUserByUsername(user_name);

		User user = userRepo.findByUserName(user_name);
		System.out.println(user);
		String newGeneratedToken = jwtUtil.generateToken(user_name);
		return new JwtResponse(user, newGeneratedToken);

	}

	@Override
	public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {

		System.out.println("able to find (jwt Service class)");

		User user = userRepo.findByUserName(user_name);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUser_name(), user.getUser_password(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + user_name);
		}

	}

	private Set getAuthority(User user) {
		System.out.println("autharity to role(jwt service)");

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole_name()));
		});
		return authorities;
	}

	private void authenticate(String user_name, String user_password) throws Exception {

		System.out.println("credentail exceptions");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user_name, user_password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
