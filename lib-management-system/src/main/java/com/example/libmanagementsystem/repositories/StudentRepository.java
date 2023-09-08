package com.example.libmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libmanagementsystem.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
