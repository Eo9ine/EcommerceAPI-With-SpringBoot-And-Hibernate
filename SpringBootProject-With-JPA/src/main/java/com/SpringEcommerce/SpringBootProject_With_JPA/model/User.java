package com.SpringEcommerce.SpringBootProject_With_JPA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Size(min = 3 , max = 20)
    @Column(name = "username")
    private String username;

    @Email
    @NotBlank
    @Column(name = "user_email")
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade =
            {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role")
    private Set<Role> userRoles = new HashSet<>();
}
