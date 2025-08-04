package com.company.docreview.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "role", nullable = false, length = 20)
    private String role;
}