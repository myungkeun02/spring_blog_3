package org.myungkeun.spring_blog_3.services.Impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseStatus;
import org.myungkeun.spring_blog_3.dto.user.ProfileResponse;
import org.myungkeun.spring_blog_3.dto.user.UpdatePasswordRequest;
import org.myungkeun.spring_blog_3.entities.User;
import org.myungkeun.spring_blog_3.jwt.JwtService;
import org.myungkeun.spring_blog_3.repositories.TokenRepository;
import org.myungkeun.spring_blog_3.repositories.UserRepository;
import org.myungkeun.spring_blog_3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<ApiResponseDto<?>> getProfileByToken(Principal connectedUser) {
        try {
            User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
            ProfileResponse response = ProfileResponse.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .role(user.getRole())
                    .build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(HttpStatus.OK.value(), ApiResponseStatus.SUCCESS.name(), response));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), e));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updatePasswordByToken(Principal connectedUser, UpdatePasswordRequest request) {
        try {
            User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), ApiResponseStatus.FAIL.name(), "비밀번호가 올바르지 않습니다"));
            }
            if (!request.getNewPassword().equals(request.getConformPassword())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), ApiResponseStatus.FAIL.name(), "newPassword와 conformPassword가 일치하지 않습니다"));
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(HttpStatus.OK.value(), ApiResponseStatus.SUCCESS.name(), "비밀번호 변경을 완료하였습니다"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), e));
        }
    }
}
