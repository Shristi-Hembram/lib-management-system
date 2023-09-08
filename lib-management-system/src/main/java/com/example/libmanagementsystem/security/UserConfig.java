package com.example.libmanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig extends WebSecurityConfiguration {
	@Value("${user.authority.student}")
	private String studentAuthority;
	private String adminAuthority;

	@Autowired
	UserService userService;

	// @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);

	}

	// @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic().and().authorizeRequests().requestMatchers(HttpMethod.GET, "/book/**")
				.hasAnyAuthority(studentAuthority, adminAuthority).requestMatchers("/book/**")
				.hasAuthority(adminAuthority).requestMatchers(HttpMethod.GET, "transaction/**")
				.hasAnyAuthority(studentAuthority, adminAuthority).requestMatchers("/transaction/**")
				.hasAuthority(studentAuthority).requestMatchers(HttpMethod.POST, "/student/**").permitAll()
				.requestMatchers("/studentById/**", "/student/all/**").hasAuthority(adminAuthority)
				.requestMatchers("student/**").hasAuthority(studentAuthority).and().formLogin();
	}

	public PasswordEncoder getPE() {
		return new BCryptPasswordEncoder();
	}

}
