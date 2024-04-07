package org.myungkeun.spring_blog_3.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdatePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String conformPassword;
}
