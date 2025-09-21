package com.SpringEcommerce.SpringBootProject_With_JPA.security;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


public class CustomUserDetailsImp implements UserDetails {

    @Autowired
    private final User user;

    public CustomUserDetailsImp(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getUserRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getUserRole().name()))
                .collect(Collectors.toList());
    }

    public Long getUserId()
    {
        return this.user.getUserId();
    }

    public String getEmail()
    {
        return this.user.getEmail();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
