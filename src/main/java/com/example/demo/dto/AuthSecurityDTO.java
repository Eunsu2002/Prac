package com.example.demo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;

public class AuthSecurityDTO extends User implements OAuth2User {

    private String aid;
    private String email;
    private String password;
    private String nickName;

    public AuthSecurityDTO(String username, String password, String email, String nickName,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.aid = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }

    @Override
    public String getName() {
        return null;
    }
}
