package com.example.Project.BackendProject.Jwt;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // use for role base ath
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private jwtAuthEntry jwtAuthEntry;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private UserDetailsService jwtService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		log.info("permiting and authenticating (web security config)");
		httpSecurity.cors();
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/authenticate", "/api/user/adduser").permitAll()

				.antMatchers("/v2/**").permitAll().antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/webjars/**").permitAll().antMatchers("/swagger-resources/**").permitAll()

				// .authorizeRequests().antMatchers("/admin/**").hasRole("admin").antMatchers("/user/**").hasRole("user")
				// .antMatchers("/**").permitAll()
				.anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(jwtAuthEntry).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * @Override public void configure(WebSecurity registry) throws Exception {
	 * registry.ignoring()
	 * .antMatchers("/docs/**").antMatchers("/actuator/**").antMatchers(
	 * "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
	 * "/configuration/security", "/swagger-ui.html", "/webjars/**"); }
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
	}

}
