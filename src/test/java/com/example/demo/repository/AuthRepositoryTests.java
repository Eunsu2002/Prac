package com.example.demo.repository;

import com.example.demo.entity.Auth;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class AuthRepositoryTests {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void insertAuth() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Auth auth = Auth.builder()
                    .aid("member"+i)
                    .password(passwordEncoder.encode("1111"))
                    .email("email"+i+"@test.com")
                    .build();
        });
    }

    @Test
    public void testRead() {
        Optional<Auth> result = authRepository.getById("member100");
        Auth auth = result.orElseThrow();

        log.info(auth);
    }

    @Test
    public void testFindByEmail() {
        Optional<Auth> result = authRepository.findByEmail("email100@test.com");
        Auth auth = result.orElseThrow();

        log.info(auth);
    }

    @Commit
    @Test
    public void testUpdate() {
        String aid = "kdw335552@kakao.com";
        String password = passwordEncoder.encode("54321");
        authRepository.updatePassword(password, aid);
    }
}
