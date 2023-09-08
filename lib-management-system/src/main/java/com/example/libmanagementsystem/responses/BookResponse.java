package com.example.libmanagementsystem.responses;

import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Genre;
import com.example.libmanagementsystem.models.Student;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookResponse {
	private int id;
	private String name;
	private Genre genre;
	private Student student;

	public static BookResponse to(Book book) {
		return BookResponse.builder().id(book.getId()).name(book.getName()).genre(book.getGenre())
				.student(book.getStudent()).build();
	}

}
