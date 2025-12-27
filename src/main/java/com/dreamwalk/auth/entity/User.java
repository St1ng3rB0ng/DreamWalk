package com.dreamwalk.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@ToString(exclude = "password")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//cause id is primary key to our user
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    // ! не виводимо в toString
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(length = 50)
    private String fullName;

    private String avatar;

    @Column(length = 250)
    private String bio;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public User(String username, String email, String hashedPassword, Role role)
    {
        this.username = username;
        this.email = email;
        this.password = hashedPassword;
        this.role = role;
        this.status = UserStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public void setPassword(String hashedPassword)
    {
        this.password = hashedPassword;
    }

    public void setStatus(UserStatus status)
    {
        this.status = status;
    }
}