package com.demo.student.management.service.impl;

import com.demo.student.management.entity.Course;
import com.demo.student.management.entity.Student;
import com.demo.student.management.repository.CourseRepository;
import com.demo.student.management.repository.StudentRepository;
import com.demo.student.management.service.CourseService;
import com.demo.student.management.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final JwtUtil jwtUtil;
    @Override
    public void registerToCourse(Long courseId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Student student = studentRepository.findByEmail(username);
        courseRepository.findById(courseId);
        ArrayList<Course> courseArrayList = new ArrayList<>();
        courseArrayList.add(courseRepository.findById(courseId).get());
        student.setEnrolledCourses(courseArrayList);
        studentRepository.save(student);


    }
}
