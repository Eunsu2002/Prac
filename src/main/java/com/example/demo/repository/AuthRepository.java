package com.example.demo.repository;

import com.example.demo.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findById(String id);
    Optional<Auth> findByUsername(String username);
    Optional<Auth> findByEmail(String email);
}
