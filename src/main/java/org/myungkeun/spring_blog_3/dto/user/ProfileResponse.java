package org.myungkeun.spring_blog_3.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProfileResponse {
    private String username;
    private String email;
    private String role;
}
