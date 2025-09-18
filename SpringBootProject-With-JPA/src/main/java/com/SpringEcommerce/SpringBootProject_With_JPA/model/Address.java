package com.SpringEcommerce.SpringBootProject_With_JPA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "street")
    @Size(min = 5, message = "Street name must be at least 5 characters or more.")
    private String streetName;

    @Column(name = "country")
    @Size(min = 4, message = "County name must be at least 4 characters or more.")
    private String country;

    @Column(name = "city")
    @Size(min = 5, message = "City name must be at least 5 characters or more.")
    private String city;

    @Column(name = "city")
    @Size(min = 5, message = "Town name must be at least 5 characters or more.")
    private String town;

    @ToString.Exclude
    @OneToOne(mappedBy = "address")
    @Column(name = "user")
    private User user;

}
