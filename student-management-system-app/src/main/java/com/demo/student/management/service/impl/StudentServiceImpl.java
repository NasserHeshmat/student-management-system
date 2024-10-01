package com.demo.student.management.service.impl;

import com.demo.student.management.entity.Student;
import com.demo.student.management.exception.CustomException;
import com.demo.student.management.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.demo.student.management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import static com.demo.student.management.constant.ErrorMessages.USER_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    @Override
    public void registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        if(studentRepository.existsByEmail(student.getEmail()))
            throw new CustomException(USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
        studentRepository.save(student);
    }
}
