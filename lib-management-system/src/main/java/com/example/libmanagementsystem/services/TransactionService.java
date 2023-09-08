package com.example.libmanagementsystem.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.libmanagementsystem.models.Book;
import com.example.libmanagementsystem.models.Student;
import com.example.libmanagementsystem.models.Transaction;
import com.example.libmanagementsystem.models.TransactionStatus;
import com.example.libmanagementsystem.models.TransactionType;
import com.example.libmanagementsystem.repositories.TransactionRepository;

@Service
public class TransactionService {
	@Value("${student.book.quota}")
	int studentBookQuota;

	@Value("${book.return.days}")
	int bookReturnDays;

	@Value("${book.fine.day}")
	int finePerDay;

	@Autowired
	StudentService studentService;

	@Autowired
	BookService bookService;

	@Autowired
	TransactionRepository transactionRepository;

	public String issueBook(int studentId, int bookId) throws Exception {
		Student student = studentService.getStudentById(studentId);
		if (student == null) {
			throw new Exception("Student does not exist. Unable to issue the book.");
		}
		if (student.getBookList().size() >= studentBookQuota) {
			throw new Exception("Maximum book issue limit reached.");

		}
		Book book = bookService.getBookById(bookId).get(0);
		if (book.getStudent() != null) {
			throw new Exception("Book already issued by someone.");
		}

		Transaction transaction = Transaction.builder().book(book).student(student)
				.transactionType(TransactionType.ISSUE).transactionStatus(TransactionStatus.PENDING)
				.transactionId(UUID.randomUUID().toString()).build();

		transactionRepository.save(transaction);
		try {
			book.setStudent(student);
			bookService.createBook(book);

			transaction.setTransactionStatus(TransactionStatus.SUCCESS);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			book.setStudent(null);
			bookService.createBook(book);

			transaction.setTransactionStatus(TransactionStatus.FAILED);
			transactionRepository.save(transaction);
		}
		return transaction.getTransactionId();
	}

	public String returnBook(int studentId, int bookId) throws Exception {
		Student student = studentService.getStudentById(studentId);
		Book book = bookService.getBookById(bookId).get(0);

		if (student == null || book == null || book.getStudent() == null
				|| book.getStudent().getStudentId() != studentId) {
			throw new Exception("Either student or book is not present. Or book is not assigned"
					+ " to the student,unable to return the book");
		}

		List<Transaction> issueTxns = transactionRepository.findByBookAndStudentAndTransactionTypeOrderByIdDesc(book,
				student, TransactionType.ISSUE);
		Transaction issueTxn = issueTxns.get(0);

		long issueTimeInMillis = issueTxn.getUpdatedOn().getTime();
		long currentTimeMillis = System.currentTimeMillis();

		long timeDiff = currentTimeMillis - issueTimeInMillis;
		long numberOfDaysPassed = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

		int fine = 0;
		if (numberOfDaysPassed > bookReturnDays) {
			fine = (int) (numberOfDaysPassed - bookReturnDays) * finePerDay;
		}

		Transaction transaction = Transaction.builder().transactionId(UUID.randomUUID().toString())
				.transactionType(TransactionType.RETURN).transactionStatus(TransactionStatus.PENDING).student(student)
				.book(book).fine(fine).build();

		transactionRepository.save(transaction);

		try {
			book.setStudent(null);
			bookService.createBook(book);

			transaction.setTransactionStatus(TransactionStatus.SUCCESS);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			book.setStudent(student);
			bookService.createBook(book);

			transaction.setTransactionStatus(TransactionStatus.FAILED);
			transactionRepository.save(transaction);
		}
		return transaction.getTransactionId();
	}

}
