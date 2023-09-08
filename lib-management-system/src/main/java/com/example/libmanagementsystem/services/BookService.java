package com.example.libmanagementsystem.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libmanagementsystem.models.Author;
import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Genre;
import com.example.libmanagementsystem.repositories.BookRepository;
import com.example.libmanagementsystem.responses.BookResponse;
import com.example.libmanagementsystem.responses.BookWithoutStudentResponse;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorService authorService;

	public void createBook(Book book) {
		Author author = authorService.createOrGet(book.getMyAuthor());
		book.setMyAuthor(author);
		bookRepository.save(book);

	}

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public List<Book> getBookByAuthor(String name) {
		// TODO Auto-generated method stub
		return bookRepository.findByMyAuthorName(name);
	}

	public List<Book> getBookByGenre(Genre valueOf) {
		// TODO Auto-generated method stub
		return bookRepository.findByGenre(valueOf);
	}

	public List<Book> getBookById(int parseInt) {
		// TODO Auto-generated method stub
		Optional<Book> bookOptional = bookRepository.findById(parseInt);
		if (bookOptional.isPresent()) {
			return Arrays.asList(bookOptional.get());
		}
		return new ArrayList<>();
	}

	public List<BookResponse> getBookByStudentName(String name) {
		// TODO Auto-generated method stub
		List<Book> bookList = Arrays.asList(bookRepository.findByStudentName(name));
		return bookList.stream().map(BookResponse::to).collect(Collectors.toList());
	}

	public List<BookWithoutStudentResponse> getBookByStudentNameWithoutStudent(String name) {
		List<Book> bookList = Arrays.asList(bookRepository.findByStudentName(name));
		return bookList.stream().map(BookWithoutStudentResponse::to).collect(Collectors.toList());
	}

}
