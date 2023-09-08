package com.example.libmanagementsystem.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.libmanagementsystem.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true)
	private String username;
	private String password;
	private String authority;

	@OneToOne(mappedBy = "user")
	@JsonIgnoreProperties("user")
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(this.authority);

		return Collections.singletonList(grantedAuthority);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
