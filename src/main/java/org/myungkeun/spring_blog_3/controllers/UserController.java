package org.myungkeun.spring_blog_3.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_3.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserController {
    private UserService userService;
}
