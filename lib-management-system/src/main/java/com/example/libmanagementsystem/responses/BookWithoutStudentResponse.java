package com.example.libmanagementsystem.responses;

import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Genre;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookWithoutStudentResponse {
	private int id;
	private String name;
	private Genre genre;

	public static BookWithoutStudentResponse to(Book book) {
		return BookWithoutStudentResponse.builder().id(book.getId()).name(book.getName()).genre(book.getGenre())
				.build();
	}

}
