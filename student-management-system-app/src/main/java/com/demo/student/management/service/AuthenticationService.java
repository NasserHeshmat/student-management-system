package com.demo.student.management.service;

import com.demo.student.management.model.AuthenticationRequest;
import com.demo.student.management.model.AuthenticationResponse;
import com.demo.student.management.model.RefreshTokenRequest;
import com.demo.student.management.model.RefreshTokenResponse;

public interface AuthenticationService {
    AuthenticationResponse createAuthenticationTokens(AuthenticationRequest authRequest);

    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
