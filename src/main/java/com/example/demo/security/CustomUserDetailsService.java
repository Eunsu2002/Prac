package com.example.demo.security;

import com.example.demo.dto.AuthSecurityDTO;
import com.example.demo.entity.Auth;
import com.example.demo.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final AuthRepository authRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername " + username);

        Optional<Auth> result = authRepository.findById(username);      // DB -> 사용자 정보 불러오기

        if (result.isEmpty()) {                                         // 결과 없는 경우 -> UserDetails에 예외처리 클래스 호충
            throw new UsernameNotFoundException("username not found");
        }

        Auth auth = result.get();

        AuthSecurityDTO authSecurityDTO = new AuthSecurityDTO(
                auth.getAid(),
                auth.getNickName(),
                auth.getPassword(),
                auth.getEmail()
        );

        log.info("authSecurityDTO");
        log.info(authSecurityDTO);

        return authSecurityDTO;
    }
}
