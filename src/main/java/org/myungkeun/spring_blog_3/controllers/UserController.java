package org.myungkeun.spring_blog_3.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.user.UpdatePasswordRequest;
import org.myungkeun.spring_blog_3.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserController {
    private UserService userService;

    @GetMapping("/proflie")
    public ResponseEntity<ApiResponseDto<?>> getUserProfileById(Principal connectedUser) {
        return userService.getProfileByToken(connectedUser);
    }

    @PutMapping("/update/password")
    public ResponseEntity<ApiResponseDto<?>> updatePasswordById(Principal connectedUser, UpdatePasswordRequest request) {
        return userService.updatePasswordByToken(connectedUser, request);
    }
}
