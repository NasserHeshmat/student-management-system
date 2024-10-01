package com.demo.student.management.repository;

import com.demo.student.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getReferenceById(Long courseId);
}
