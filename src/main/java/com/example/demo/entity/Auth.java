package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "aid")
    private int aid;

    @NotEmpty
    @Column(unique = true, name = "email")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(unique = true, name = "nick_name")
    private String nickName;

    public void setAuth(int aid){this.aid = aid;}

    public void chagePassword(String password) {this.password = password;}

    public void chageNickName(String nickName) {this.nickName = nickName;}

    public void changeEmail(String email) {this.email = email;}

}
