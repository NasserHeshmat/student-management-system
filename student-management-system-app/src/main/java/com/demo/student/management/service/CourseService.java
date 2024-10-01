package com.demo.student.management.service;

import com.demo.student.management.entity.Course;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface CourseService {
    void registerToCourse(Long courseId);

    List<Course> findAll();

    byte[] getCourseScheduleAsPdf(Long courseId);

    void cancelCourseRegisteration(Long courseId);
}
