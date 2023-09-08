package com.example.libmanagementsystem.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Author {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int authorId;

	@Column(nullable = false) // making the column noy null
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	@OneToMany(mappedBy = "myAuthor")
	@JsonIgnoreProperties(value = { "myAuthor", "createdOn", "updatedOn" })
	List<Book> bookList;

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

}
