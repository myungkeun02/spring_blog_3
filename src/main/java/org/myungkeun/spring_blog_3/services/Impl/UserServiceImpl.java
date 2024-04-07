package org.myungkeun.spring_blog_3.services.Impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.user.UpdatePasswordRequest;
import org.myungkeun.spring_blog_3.jwt.JwtService;
import org.myungkeun.spring_blog_3.repositories.TokenRepository;
import org.myungkeun.spring_blog_3.repositories.UserRepository;
import org.myungkeun.spring_blog_3.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public ResponseEntity<ApiResponseDto<?>> getProfileByToken() {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updatePasswordByToken(UpdatePasswordRequest request) {
        return null;
    }
}
