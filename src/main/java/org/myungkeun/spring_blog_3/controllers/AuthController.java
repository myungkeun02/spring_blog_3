package org.myungkeun.spring_blog_3.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.auth.LoginRequest;
import org.myungkeun.spring_blog_3.dto.auth.RegisterRequest;
import org.myungkeun.spring_blog_3.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> registerUser(
            @RequestBody RegisterRequest request
    ) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<?>> loginUser(
            @RequestBody LoginRequest request
    ) {
        return authService.loginUser(request);
    }
}
