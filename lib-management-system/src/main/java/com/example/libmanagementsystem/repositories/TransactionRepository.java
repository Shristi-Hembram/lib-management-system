package com.example.libmanagementsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Student;
import com.example.libmanagementsystem.models.Transaction;
import com.example.libmanagementsystem.models.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	List<Transaction> findByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student,
			TransactionType transactionType);

}
