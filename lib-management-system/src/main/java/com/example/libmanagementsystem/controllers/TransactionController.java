package com.example.libmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.libmanagementsystem.security.User;
import com.example.libmanagementsystem.services.TransactionService;

@RestController
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/transaction/issue")
	public String issueBook(@RequestParam("bookId") int bookId) throws Exception {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer studentId = user.getStudent().getStudentId();
		return transactionService.issueBook(studentId, bookId);
	}
	
	@PostMapping("/transaction/return")
	public String returnBook(@RequestParam("bookId") int bookId) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Integer studentId = user.getStudent().getStudentId();
		return transactionService.returnBook(studentId, bookId);
	}

}
