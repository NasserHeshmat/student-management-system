package com.demo.student.management.controller;

import com.demo.student.management.model.RefreshTokenRequest;
import com.demo.student.management.model.RefreshTokenResponse;
import com.demo.student.management.service.AuthenticationService;
import com.demo.student.management.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@AllArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        RefreshTokenResponse response = authenticationService.refreshToken(refreshTokenRequest);

        return ResponseEntity.ok(response);
    }
}
