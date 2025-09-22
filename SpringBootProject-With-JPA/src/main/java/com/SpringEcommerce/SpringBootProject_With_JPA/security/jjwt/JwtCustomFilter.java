package com.SpringEcommerce.SpringBootProject_With_JPA.security.jjwt;

import com.SpringEcommerce.SpringBootProject_With_JPA.security.CustomUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtCustomFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtils;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    private final Logger logger = LoggerFactory.getLogger(JwtCustomFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try
        {
            String jwt = jwtUtils.getTokenFromRequest(request);
            logger.debug("Jwt token from header: {}", jwt);

            if(jwt != null && jwtUtils.isTokenValid(jwt))
            {
                String username = jwtUtils.extractUserName(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(userDetails != null)
                {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authentication.setDetails(
                            new WebAuthenticationDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    filterChain.doFilter(request,response);
                }
            }
        }
        catch(Exception e){
            logger.error("Could not authenticate user: {}", e.getMessage());
        }

    }
}
