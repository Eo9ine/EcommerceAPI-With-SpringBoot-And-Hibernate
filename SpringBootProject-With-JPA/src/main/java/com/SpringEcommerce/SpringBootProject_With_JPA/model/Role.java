package com.SpringEcommerce.SpringBootProject_With_JPA.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", length = 15)
    private ApplicationRoles userRole;

    @ManyToMany(mappedBy = "userRole")
    @Column(name = "user")
    private User user;
}
