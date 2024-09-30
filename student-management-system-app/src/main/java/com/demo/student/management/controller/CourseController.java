package com.demo.student.management.controller;

import com.demo.student.management.entity.Course;
import com.demo.student.management.repository.CourseRepository;
import com.demo.student.management.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

//    @PostMapping
//    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
//        Course savedCourse = courseRepository.save(course);
//        return ResponseEntity.ok(savedCourse);
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerToCourse(@RequestParam Long courseId) {
        // Implement logic to register a user to a course
        // (Fetch user and course, add user to course, and save)
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
        // Implement logic to generate PDF for the course schedule
        byte[] pdfContent = {}; // Replace with actual PDF generation logic
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=course_schedule.pdf")
                .body(pdfContent);
    }
}
