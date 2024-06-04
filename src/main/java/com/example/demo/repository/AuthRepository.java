package com.example.demo.repository;

import com.example.demo.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Integer> {

    // 주어진 id, nickName, email 로 Auth 엔티티가 있는지 확인하는 함수, 있으면 Optional<Auth> / 없으면 Optional.empty() 반환
    Optional<Auth> findById(String id);
    Optional<Auth> findByNickName(String nickName);
    Optional<Auth> findByEmail(String email);
}
