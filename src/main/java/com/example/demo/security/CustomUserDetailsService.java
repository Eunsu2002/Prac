package com.example.demo.security;

import com.example.demo.dto.AuthSecurityDTO;
import com.example.demo.entity.Auth;
import com.example.demo.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : " + username );

        // DB 에 등록된 사용자 정보 불러오기
        Optional<Auth> result = authRepository.findById(username);

        // 결과가 없는 경우 -> UserDetails 에 있는 예외 처리 클래스 호출
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("username not found...........");
        }

        Auth auth = result.get();

        // UserDetails 객체로 반환하는 userDetails 를 생성
        AuthSecurityDTO authSecurityDTO = new AuthSecurityDTO(
                auth.getAid(),
                auth.getPassword(),
                auth.getEmail(),
                auth.getNickName()
        );

        log.info("AuthSecurityDTO : " + authSecurityDTO);

        return AuthSecurityDTO;
    }
}
