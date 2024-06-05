package com.example.demo.service;

import com.example.demo.dto.AuthDTO;
import com.example.demo.entity.Auth;
import com.example.demo.repository.AuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public Auth join(AuthDTO authDTO) throws AidExistException, EmailExistException {
        int aid = authDTO.getAid();
        String email = authDTO.getEmail();

        if (authRepository.existsById(aid)) {
            throw new AidExistException("이미 존재하는 아이디입니다.");
        }

        Auth auth = modelMapper.map(authDTO, Auth.class);
        auth.chagePassword(passwordEncoder.encode(authDTO.getPassword()));

        log.info("생성된 auth : " + auth);

        return authRepository.save(auth);
    }

    @Override
    public AuthDTO info(int id) {
        Optional<Auth> authOptional = authRepository.findById(id);
        Auth auth = authOptional.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        AuthDTO authDTO = modelMapper.map(auth, AuthDTO.class);

        log.info("info AuthDTO : " + authDTO);
        authDTO.setAid(auth.getAid());
        return authDTO;
    }

    @Override
    public void modify(AuthDTO authDTO) {
        Optional<Auth> authOptional = authRepository.findById(authDTO.getAid());
        Auth auth = authOptional.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        auth.chagePassword(passwordEncoder.encode(authDTO.getPassword()));
        auth.chageNickName(authDTO.getNickName());
        auth.changeEmail(authDTO.getEmail());
        authRepository.save(auth);
    }

    @Override
    public void remove(int id) {
        authRepository.deleteById(id);
    }

}
