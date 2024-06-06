package com.example.demo.repository;

import com.example.demo.entity.Auth;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Integer> {

    // 주어진 id, nickName, email 로 Auth 엔티티가 있는지 확인하는 함수, 있으면 Optional<Auth> / 없으면 Optional.empty() 반환
    Optional<Auth> findById(String id);
    Optional<Auth> findByNickName(String nickName);
    Optional<Auth> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Auth a set a.password = :password where a.aid = :aid")
    void updatePassword(@Param("password") String password, @Param("aid") String aid);
}
