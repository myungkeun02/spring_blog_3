package org.myungkeun.spring_blog_3.services;

import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.auth.LoginRequest;
import org.myungkeun.spring_blog_3.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> registerUser(RegisterRequest request);
    ResponseEntity<ApiResponseDto<?>> loginUser(LoginRequest request);
}
