package com.demo.student.management.controller;

import com.demo.student.management.entity.Student;
import com.demo.student.management.model.ConfirmationResponse;
import com.demo.student.management.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.demo.student.management.constant.ErrorMessages.STUDENT_CREATED_SUCCESSFULLY;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<ConfirmationResponse> registerUser(@Valid @RequestBody Student student) {
        studentService.registerStudent(student);
        return ResponseEntity.ok(new ConfirmationResponse(STUDENT_CREATED_SUCCESSFULLY));
    }
}
