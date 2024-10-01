package com.demo.student.management.controller;

import com.demo.student.management.entity.Course;
import com.demo.student.management.service.CourseService;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.index.qual.Positive;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.demo.student.management.constant.ErrorMessages.INVALID_COURSE_ID;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerToCourse(@Valid @RequestParam @Min(value = 1) Long courseId) {
        courseService.registerToCourse(courseId);
        return ResponseEntity.ok("User registered to course successfully");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelCourseRegistration(@Valid @RequestParam @Min(value = 1) Long courseId) {
    courseService.cancelCourseRegisteration(courseId);
    return ResponseEntity.ok("Course registration canceled successfully");
    }

    @GetMapping("/schedule/pdf")
    public ResponseEntity<byte[]> getCourseScheduleAsPdf(@Valid @RequestParam @Min(value = 1) Long courseId) {
        byte[] pdfContent = courseService.getCourseScheduleAsPdf(courseId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=course_schedule.pdf")
                .body(pdfContent);
    }
}
