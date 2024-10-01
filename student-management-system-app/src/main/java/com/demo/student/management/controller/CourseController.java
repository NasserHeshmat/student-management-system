package com.demo.student.management.controller;

import com.demo.student.management.entity.Course;
import com.demo.student.management.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> registerToCourse(@RequestParam Long courseId) {
        courseService.registerToCourse(courseId);
        return ResponseEntity.ok("User registered to course successfully");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelCourseRegistration(@RequestParam Long userId, @RequestParam Long courseId) {
        // Implement logic to cancel a user's registration for a course
        return ResponseEntity.ok("Course registration canceled successfully");
    }

    @GetMapping("/schedule/pdf")
    public ResponseEntity<byte[]> getCourseScheduleAsPdf(@RequestParam Long courseId) {
        byte[] pdfContent = courseService.getCourseScheduleAsPdf(courseId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=course_schedule.pdf")
                .body(pdfContent);
    }
}
