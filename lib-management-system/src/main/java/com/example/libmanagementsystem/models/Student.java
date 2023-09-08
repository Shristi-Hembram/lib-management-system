package com.example.libmanagementsystem.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.userdetails.User;

import com.example.libmanagementsystem.security.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int studentId;

	private String name;
	private int age;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(unique = true)
	private String phoneNumber;

	@OneToMany(mappedBy = "student")
	@JsonIgnoreProperties(value = { "student", "transactionList" })
	private List<Book> bookList;

	@OneToMany(mappedBy = "student")
	@JsonIgnoreProperties(value = { "student", "book" })
	private List<Transaction> transactionList;

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

	// we're going to set this user property as foreign key
	@OneToOne
	@JoinColumn
	@JsonIgnoreProperties("student")
	private User user;

}
