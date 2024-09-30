//package com.demo.student.management.controller;
//
//import com.demo.student.management.model.AuthenticationRequest;
//import com.demo.student.management.service.CustomUserDetailsService;
//import com.demo.student.management.util.JwtUtil;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@AllArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationManager authenticationManager;
//    private final CustomUserDetailsService userDetailsService;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("/authenticate")
//    public String createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
//            );
//        } catch (Exception e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
//
//        return jwtUtil.generateToken(userDetails.getUsername());
//    }
//}
//
