package com.andrew.filosofia.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    UserDetails findByEmailToReturnUserDetails(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
