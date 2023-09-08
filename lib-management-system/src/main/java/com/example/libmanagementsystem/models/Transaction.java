package com.example.libmanagementsystem.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String transactionId;

	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value = "transactionList")
	private Student student;

	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value = { "transactionList", "student" })
	private Book book;

	@Enumerated(value = EnumType.STRING)
	private TransactionType transactionType;

	@Enumerated(value = EnumType.STRING)
	private TransactionStatus transactionStatus;

	private Integer fine;

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

}
