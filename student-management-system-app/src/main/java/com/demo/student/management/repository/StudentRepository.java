package com.demo.student.management.repository;

import com.demo.student.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

    boolean existsByEmail(String email);
}

