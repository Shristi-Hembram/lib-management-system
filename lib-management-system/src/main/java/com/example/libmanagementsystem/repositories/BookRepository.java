package com.example.libmanagementsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Genre;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByMyAuthorName(String authorname);

	List<Book> findByName(String bookName);

	List<Book> findByGenre(Genre genre);

	// Writing query to find the name of student from the DB
	@Query("select b from Book b where b.student.name = ?1")
	Book findByStudentName(String name);

}
