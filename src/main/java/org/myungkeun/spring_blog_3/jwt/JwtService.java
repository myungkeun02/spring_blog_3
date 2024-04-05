package org.myungkeun.spring_blog_3.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.access-token.expiration}")
    private long accessExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    // BASE64URL 로 인코딩된 시크릿키를 디코딩하여 키 객체 생성후 반환
    private Key getSignInKey() {

        byte[] ketBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(ketBytes);
    }

    // 주어진 토큰을 파싱하여 모든 클레임 반환
    private Claims extractAllClaim(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 만료시간 추출
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 토큰 생성 로직 (분리)
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 주어진 토큰에서 특정 클레임 추출
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    // 주어진 토큰에서 username 추출
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 주어진 사용자 정보를 기반으로 리프레쉬토큰 생성
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    // 주어진 사용자 정보를 기반으로 액세스토큰 토큰 생성
    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, accessExpiration);
    }

    // 토큰 유효성 검사
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // 토큰 만료 검사
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
