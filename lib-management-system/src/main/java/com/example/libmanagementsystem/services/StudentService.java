package com.example.libmanagementsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.libmanagementsystem.models.Student;
import com.example.libmanagementsystem.repositories.StudentRepository;
import com.example.libmanagementsystem.security.User;
import com.example.libmanagementsystem.security.UserService;

@Service
public class StudentService {
	@Value("${user.authority.student}")
	private String studentAuthority;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void createStudent(Student student) {
		User user = student.getUser();
		user.setAuthority(studentAuthority);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.createUser(user);
		studentRepository.save(student);
	}

	public Student getStudentById(int id) {
		return studentRepository.findById(id).orElse(null);
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

}
