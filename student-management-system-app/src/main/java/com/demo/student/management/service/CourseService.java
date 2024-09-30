package com.demo.student.management.service;

import com.demo.student.management.entity.Course;

import java.util.List;

public interface CourseService {
    void registerToCourse(Long courseId);

    List<Course> findAll();
}
