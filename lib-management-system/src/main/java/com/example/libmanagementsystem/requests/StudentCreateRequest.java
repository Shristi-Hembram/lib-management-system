package com.example.libmanagementsystem.requests;

import com.example.libmanagementsystem.models.Genre;
import com.example.libmanagementsystem.models.Student;
import com.example.libmanagementsystem.security.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StudentCreateRequest {

	@NotBlank
	private String name;

	@Positive
	private int age;
	private String phoneNumber;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 8, max = 15)
	private String password;

	public Student to() {
		return Student.builder().name(name).age(age).email(email).phoneNumber(phoneNumber)
				.user(User.builder().username(this.email).password(this.password).build()).build();
	}

}
