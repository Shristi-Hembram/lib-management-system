package com.example.libmanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.libmanagementsystem.models.Student;
import com.example.libmanagementsystem.requests.StudentCreateRequest;
import com.example.libmanagementsystem.security.User;
import com.example.libmanagementsystem.services.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;

	// creating a new id
	@PostMapping("/student")
	public void createStudent(@Valid @RequestParam StudentCreateRequest studentCreateRequest) {
		studentService.createStudent(studentCreateRequest.to());
	}

	// Getting list of all students
	// Authority --> Admin
	@GetMapping("/student/all")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	// Get details of particular studeny
	// Authority --> Admin
	@GetMapping("/studentById/{studentId}")
	public Student getStudentById(@PathVariable("studentId") int studentId) {
		return studentService.getStudentById(studentId);
	}

	@GetMapping("/student")
	public Student getStudent() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer studentId = user.getStudent().getStudentId();
		return studentService.getStudentById(studentId);
	}

}
