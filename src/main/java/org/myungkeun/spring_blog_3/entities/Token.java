package org.myungkeun.spring_blog_3.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_token")
public class Token {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @NotNull
    private String accessToken;

    @Column(unique = true)
    @NotNull
    private String refreshToken;

    @Enumerated
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
