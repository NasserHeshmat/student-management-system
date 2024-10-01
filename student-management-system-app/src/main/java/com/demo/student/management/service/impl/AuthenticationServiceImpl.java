package com.demo.student.management.service.impl;

import com.demo.student.management.model.AuthenticationRequest;
import com.demo.student.management.model.AuthenticationResponse;
import com.demo.student.management.model.RefreshTokenRequest;
import com.demo.student.management.model.RefreshTokenResponse;
import com.demo.student.management.service.AuthenticationService;
import com.demo.student.management.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final Map<String, Boolean> usedRefreshTokens = new ConcurrentHashMap<>();
    @Override
    public AuthenticationResponse createAuthenticationTokens(AuthenticationRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());

        return AuthenticationResponse.builder()
                .token(jwtUtil.generateToken(userDetails.getUsername(),false))
                .refreshToken(jwtUtil.generateToken(userDetails.getUsername(),true))
                .build();
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        if (jwtUtil.isTokenExpired(refreshTokenRequest.getRefreshToken()) || !jwtUtil.isRefreshToken(refreshTokenRequest.getRefreshToken())) {
            //todo
        }

        if (usedRefreshTokens.containsKey(refreshTokenRequest.getRefreshToken())) {
            //todo
        }
        usedRefreshTokens.put(refreshTokenRequest.getRefreshToken(), true);

        String username = jwtUtil.extractUsername(refreshTokenRequest.getRefreshToken());
        return new RefreshTokenResponse(jwtUtil.generateToken(username, false));

    }
}
