package com.example.libmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.libmanagementsystem.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query(value = "select a from Author a where a.email= :email")
	Author findAuthor(String email);

}
