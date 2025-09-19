package com.SpringEcommerce.SpringBootProject_With_JPA.security;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.User;
import com.SpringEcommerce.SpringBootProject_With_JPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try
        {
            User user = userRepository.findByUsername(username);
            return new CustomUserDetailsImp(user);
        }
        catch (UsernameNotFoundException ex)
        {
            throw new UsernameNotFoundException("Username not found!!", ex);
        }
    }
}
