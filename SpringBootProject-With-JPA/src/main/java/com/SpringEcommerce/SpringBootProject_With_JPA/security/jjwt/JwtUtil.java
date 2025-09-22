package com.SpringEcommerce.SpringBootProject_With_JPA.security.jjwt;

import com.SpringEcommerce.SpringBootProject_With_JPA.security.CustomUserDetailsImp;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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
import java.util.function.Function;
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
        logger.debug("Claims: {}", claims);
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


    public boolean isTokenValid(String token)
    {
        try
        {
            Jwts.parser()
                    .setSigningKey(signatureKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        catch(SignatureException ex)
        {
            logger.error("Invalid Jwt signature: {}", ex.getMessage());
        }
        catch(MalformedJwtException ex)
        {
            logger.error("Invalid Jwt token: {}", ex.getMessage());

        }
        catch(ExpiredJwtException ex)
        {
            logger.error("Jwt token is expired: {}", ex.getMessage());
        }
        catch(UnsupportedJwtException ex)
        {
            logger.error("Jwt is unsupported: {}", ex.getMessage());
        }
        catch(IllegalArgumentException ex)
        {
            logger.error("Jwt claims string is empty: {}", ex.getMessage());

        }

        return false;
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    //Extracting claims
    public <T> T extractClaims(String token, Function<Claims , T> claimResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signatureKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Date extractExpiration(String token)
    {
        return extractClaims(token, Claims::getExpiration );
    }

    //Refreshing
    public String refreshToken(String token)
    {
        Claims oldClaims = extractAllClaims(token);

        return Jwts.builder()
                .claims(oldClaims)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusMillis(expirationTime)))
                .signWith(signatureKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    //helper method
    public String getTokenFromRequest(HttpServletRequest request)
    {
        final String BEARER_TOKEN = "bearer ";
        String jwt = request.getHeader("Authentication");
        logger.debug("Jwt token: {}", jwt);

        if(jwt != null && jwt.startsWith(BEARER_TOKEN))
        {
            return jwt.substring(7);
        }

        logger.debug("token is not found!");
        return null;
    }

    public String extractUserName(String token)
    {
        return extractClaims(token , Claims::getSubject);
    }

}
