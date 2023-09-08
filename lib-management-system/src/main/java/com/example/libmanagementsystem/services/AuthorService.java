package com.example.libmanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libmanagementsystem.models.Author;
import com.example.libmanagementsystem.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	public Author createOrGet(Author author) {
		// we will find the Author from DB if it already exists
		Author authorFromDB = authorRepository.findAuthor(author.getEmail());

		// If it does not exist then we will create a new author and save it in DB
		if (authorFromDB == null) {
			authorFromDB = authorRepository.save(author);
		}
		return authorFromDB;
	}

}
