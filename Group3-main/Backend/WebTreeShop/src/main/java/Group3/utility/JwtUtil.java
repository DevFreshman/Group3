package Group3.utility;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Group3.Enum.RoleType;
import Group3.configs.jwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Autowired
    private final jwtConfig jwtConfig;

    public JwtUtil(jwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessTK(UUID id, RoleType role) {
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExAccessTk()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshTK( UUID id) {
        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExRefreshTk()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public LocalDateTime generateExTime() {
        long exTime = System.currentTimeMillis() + jwtConfig.getExRefreshTk();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(exTime), ZoneId.systemDefault());
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public Claims getJwtClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public UUID getUuid(String token){
        Claims claims = getJwtClaims(token);
        return UUID.fromString(claims.getSubject());
    }

    public RoleType geRoleType(String token){
        Claims claims =getJwtClaims(token);
        return RoleType.valueOf(claims.get("role",String.class));
    }
}
