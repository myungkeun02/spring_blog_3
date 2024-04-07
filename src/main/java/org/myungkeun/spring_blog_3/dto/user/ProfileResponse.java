package org.myungkeun.spring_blog_3.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.spring_blog_3.entities.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProfileResponse {
    private String username;
    private String email;
    private Role role;
}
