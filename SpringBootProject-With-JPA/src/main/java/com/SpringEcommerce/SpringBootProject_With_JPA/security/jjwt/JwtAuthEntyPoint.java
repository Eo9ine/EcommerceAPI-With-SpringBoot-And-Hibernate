package com.SpringEcommerce.SpringBootProject_With_JPA.security.jjwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthEntyPoint implements AuthenticationEntryPoint {

    ObjectMapper objectMapper = new ObjectMapper();
    final Logger logger = LoggerFactory.getLogger(JwtAuthEntyPoint.class);

    final

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        try
        {
            Map<String,Object> body = new HashMap<>();

            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("Error", "Unauthorized");
            body.put("message", "Authentication failed");
            body.put("path", request.getServletPath());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            objectMapper.writeValue(response.getOutputStream(), body);

        }
        catch (Exception e)
        {
            logger.error("Failed to send error response; {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Authentication error");
        }

    }
}
