package com.example.libmanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Genre;
import com.example.libmanagementsystem.requests.BookCreateRequest;
import com.example.libmanagementsystem.requests.BookFilterKey;
import com.example.libmanagementsystem.responses.BookResponse;
import com.example.libmanagementsystem.responses.BookWithoutStudentResponse;
import com.example.libmanagementsystem.services.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/book")
	public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
		bookService.createBook(bookCreateRequest.to());
	}

	@GetMapping("/book/all")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

	// Filtering the books
	@GetMapping("/book")
	public List<Book> getBook(@RequestParam("filterKey") String filterKey,
			@RequestParam("filterValue") String filterValue) throws Exception {
		BookFilterKey bookFilterKey = BookFilterKey.valueOf(filterValue);
		switch (bookFilterKey) {
		case AUTHOR_NAME:
			return bookService.getBookByAuthor(filterValue);
		case BOOK_NAME:
			return bookService.getBookByAuthor(filterValue);
		case BOOK_ID:
			return bookService.getBookById(Integer.parseInt(filterValue));
		case GENRE:
			return bookService.getBookByGenre(Genre.valueOf(filterValue));
		default:
			throw new Exception("Wrong filter type passed");
		}

	}

	@GetMapping("/bookByStudent")
	public List<BookResponse> getBookByStudent(@RequestParam("name") String name) {
		return bookService.getBookByStudentName(name);
	}

	@GetMapping("/bookByStudent2")
	public List<BookWithoutStudentResponse> getBooks(@RequestParam("name") String name) {
		return bookService.getBookByStudentNameWithoutStudent(name);
	}

}
