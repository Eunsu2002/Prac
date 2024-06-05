package com.example.demo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class AuthSecurityDTO extends User implements OAuth2User {

    private String aid;
    private String email;
    private String password;
    private String nickName;

    public AuthSecurityDTO(int aid, String username, String password, String email,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.aid = username;
        this.password = password;
        this.email = email;
        this.nickName = username;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }
}
