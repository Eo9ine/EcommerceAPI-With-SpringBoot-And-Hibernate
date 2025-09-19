package com.SpringEcommerce.SpringBootProject_With_JPA.security.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.http.HttpRequest;
import java.security.Key;

public class JwtUtil {

    @Value("${spring.application.SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${spring.application.EXPIRATION_TIME}")
    private String EXPIRATION_TIME;

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public Key signatureKey() { return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); }

    public String extractJwtFromRequest(HttpServletRequest request)
    {
        final String BEARER = "Bear ";

        String token = request.getHeader("Authorization");
        logger.debug("Authorization: {} ", token);

        if(token != null && token.startsWith(BEARER))
        {
            return token.substring(7);
        }

        logger.debug("Jwt token not found in request headers: {}", token);
        return null;
    }

    public String generateJwt()
    {
        return "";
    }

    public Jwt<Claims> parseJwt(String token)
    {

    }

}
