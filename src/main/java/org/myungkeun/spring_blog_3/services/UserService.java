package org.myungkeun.spring_blog_3.services;

import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.user.UpdatePasswordRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ApiResponseDto<?>> getProfileByToken();

    ResponseEntity<ApiResponseDto<?>> updatePasswordByToken(UpdatePasswordRequest request);
}
