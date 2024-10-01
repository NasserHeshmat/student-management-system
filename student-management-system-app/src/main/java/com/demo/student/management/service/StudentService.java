package com.demo.student.management.service;

import com.demo.student.management.entity.Student;

import javax.validation.Valid;

public interface StudentService {
    void registerStudent(@Valid Student student);
}
