package com.demo.student.management.controller;


import com.demo.student.management.entity.Student;
import com.demo.student.management.model.AuthenticationRequest;
import com.demo.student.management.model.AuthenticationResponse;
import com.demo.student.management.repository.StudentRepository;
import com.demo.student.management.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.demo.student.management.util.JwtUtil;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class StudentController {

    private final StudentRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        userRepository.save(student);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.createAuthenticationTokens(authRequest));
    }
}
