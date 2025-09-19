package com.SpringEcommerce.SpringBootProject_With_JPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(EntityListeners.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", length = 15)
    private ApplicationRoles userRole;

    @ManyToMany(mappedBy = "userRoles")
    @Column(name = "user")
    private List<User> user = new ArrayList<>();
}
