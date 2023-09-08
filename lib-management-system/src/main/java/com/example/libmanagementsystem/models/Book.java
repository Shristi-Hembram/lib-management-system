package com.example.libmanagementsystem.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
//@Entity

public class Book {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@Column(name = "bookname")
	private String name;

	@Enumerated(value = EnumType.STRING)
	private Genre genre;

	private int cost;

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value = "booklist")
	private Author myAuthor;

	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value = { "bookList", "transactionList" })
	private Student student;

	@OneToMany(mappedBy = "book")
	@JsonIgnoreProperties(value = { "book", "student" })
	private List<Transaction> transactionList;

}
