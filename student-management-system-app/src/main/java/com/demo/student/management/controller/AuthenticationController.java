package com.demo.student.management.controller;

import com.demo.student.management.model.AuthenticationRequest;
import com.demo.student.management.model.AuthenticationResponse;
import com.demo.student.management.model.RefreshTokenRequest;
import com.demo.student.management.model.RefreshTokenResponse;
import com.demo.student.management.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.createAuthenticationTokens(authRequest));
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshTokenResponse response = authenticationService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(response);
    }
}
