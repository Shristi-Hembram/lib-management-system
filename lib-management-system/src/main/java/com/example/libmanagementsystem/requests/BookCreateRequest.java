package com.example.libmanagementsystem.requests;

import com.example.libmanagementsystem.models.Author;
import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Genre;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookCreateRequest {
	@NotBlank
	private String name;

	@Positive
	private int cost;

	@NotNull
	private Genre genre;

	@NotBlank
	private String authorName;

	@NotBlank
	@Email
	private String email;

	public Book to() {
		Author author = Author.builder().name(authorName).email(email).build();
		return Book.builder().name(name).genre(genre).myAuthor(author).build();
	}

}
