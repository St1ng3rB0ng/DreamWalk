package com.dreamwalk.auth.repository;

import com.dreamwalk.auth.entity.User;
import com.dreamwalk.auth.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndStatus(String email, UserStatus status);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
