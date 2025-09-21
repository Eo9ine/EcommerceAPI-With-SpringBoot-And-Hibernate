package com.SpringEcommerce.SpringBootProject_With_JPA.security.jjwt;

import com.SpringEcommerce.SpringBootProject_With_JPA.security.CustomUserDetailsImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtil {

    @Value("${spring.application.SECRET_KEY}")
    private String secretKey;

    @Value("${spring.application.EXPIRATION_TIME}")
    private Long expirationTime;

    private String jwtIssuer;

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public Key signatureKey() { return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); }

    public String generateTokenWithDefaultClaims(CustomUserDetailsImp userDetailsImp)
    {
        Map<String , Object> claims = new HashMap<>();

        claims.put("UserId",userDetailsImp.getUserId());
        claims.put("Email", userDetailsImp.getEmail());
        claims.put("Authorities", getAuthorities(userDetailsImp));
        claims.put("Issuer", jwtIssuer);

        return buildToken(claims, userDetailsImp.getUsername());
    }

    public String generateTokenWithExtendedClaims(CustomUserDetailsImp userDetailsImp , Map<String , Object> customClaims)
    {
        Map<String , Object> claims = new HashMap<>();

        claims.put("UserId",userDetailsImp.getUserId());
        claims.put("Email", userDetailsImp.getEmail());
        claims.put("Authorities", getAuthorities(userDetailsImp));
        claims.put("Issuer", jwtIssuer);

        customClaims.putAll(claims);

        return buildToken(customClaims, userDetailsImp.getUsername());
    }

    private String buildToken(Map<String, Object> claims, String username)
    {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuer(jwtIssuer)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expirationTime)))
                .signWith(signatureKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    public List<String> getAuthorities(CustomUserDetailsImp userDetails)
    {
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }




}
