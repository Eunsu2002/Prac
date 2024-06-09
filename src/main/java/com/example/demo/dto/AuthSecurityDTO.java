package com.example.demo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class AuthSecurityDTO extends User implements OAuth2User {

    private int aid;
    private String email;
    private String password;
    private String nickName;

    public AuthSecurityDTO(int aid, String username, String password, String email) {
        super(username, password, Collections.EMPTY_LIST);          // 빈 리스트 전달

        this.aid = aid;
        this.password = password;
        this.email = email;
        this.nickName = username;
    }

    @Override
    public String getName() {
        return nickName;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }
}

